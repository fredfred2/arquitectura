package com.example.ejb;

import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Bid;
import com.example.model.Image;
import com.example.model.Item;
import com.example.stub.StubbedAuctions;
import com.example.util.AuctionEndedException;
import com.example.util.AuctionListView;
import com.example.util.AuctionStatus;
import com.example.util.AuctionUtil;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuctionFacade {

    private static final Logger LOG = Logger.getLogger(AuctionFacade.class.getName());
    private AuctionListView[] auctionItemsListed;
    @Inject
    private ItemFacade itemFacade;
    @Inject
    private ImageFacade imageFacade;
    @Inject
    private BidFacade bidFacade;
    @Inject
    private WatchFacade watchFacade;

    public AuctionFacade() {
    }

    public AuctionListView[] getAllAuctionListView() {
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        // Get all the auctions
        Auction[] auctionList = auctions.values().toArray(new Auction[0]);
        // Get a count to create the array
        auctionItemsListed = new AuctionListView[auctionList.length];
        // Construct an object that JSF can use to show the auction item
        int i = 0;
        for (Auction a : auctionList) {
            int auctionId = a.getAuctionId();
            Item item = a.getItem();
            int imageId;
            if (item.getImage() != null) {
                imageId = item.getImage().getImageId();
            } else {
                imageId = 0;
            }
            String title = item.getTitle();
            String desc = item.getDescription();
            String cond = item.getCondition();
            int numBids = bidFacade.numBids(auctionId);
            int numWatches = watchFacade.numWatches(auctionId);
            float currPrice = a.getCurrPrice();
            float bidPrice = a.getCurrPrice() + a.getIncrement();
            String timeRemaining = null;
            // Convert the integer value to its enum type
            switch (AuctionStatus.values()[a.getStatus()]) {
                case ACTIVE:
                    try {
                        timeRemaining = AuctionUtil.getTimeRemaining(a.getEndDate());
                    } catch (AuctionEndedException ae) {
                        timeRemaining = "Ended";
                    }
                    break;
                case CANCELLED:
                    timeRemaining = "Cancelled";
                    break;
                case ENDED:
                    timeRemaining = "Ended";
                    break;
            }
            // We have all the data, create the object
            auctionItemsListed[i] = new AuctionListView(auctionId, imageId, title, desc, cond, numBids, numWatches, currPrice, bidPrice, timeRemaining);
            i++;
        }
        return auctionItemsListed;
    }

    public void addAuction(Auction auction) {
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        Item item = auction.getItem();
        item = itemFacade.addItem(item);  // add the Item and generate the primary key

        Image image = item.getImage();
        image = imageFacade.addImage(image);
        item.setImage(image);
        auction.setItem(item);

        // Put the generated id into the Auction
        int auctionId = StubbedAuctions.getNextId();
        auction.setAuctionId(auctionId);
        auctions.put(auction.getAuctionId(), auction);
    }

    public boolean removeAuction(int auctionId) {
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        Auction auction = auctions.get(auctionId);
        itemFacade.removeItem(auction.getItem());
        auctions.remove(auction.getAuctionId());
        return true;
    }

    public boolean cancelAuction(int auctionId) {
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        Auction auction = auctions.get(auctionId);
        auction.setStatus(AuctionStatus.CANCELLED.ordinal());
        return true;
    }

    public boolean endAuction(int auctionId) {
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        Auction auction = auctions.get(auctionId);
        auction.setStatus(AuctionStatus.ENDED.ordinal());
        return true;
    }

    public Auction findAuctionById(int auctionId) {
        Auction auction = null;
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        return auctions.get(auctionId);
    }

    // Insert a new Bid
    public void addBid(AuctionUser bidder, Auction auction, float amount) {
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setBidTime(new Date());
        bid.setBidder(bidder);
        bid.setAuction(auction);
        bidFacade.addBid(bid);
        // Set the current price to the bid amount
        auction.setCurrPrice(amount);
        // See if the increment needs updating
        auction.setIncrement(AuctionUtil.defineIncrement(amount));
        auctions.put(auction.getAuctionId(), auction);
    }

    // Return true if add was successful and false if already watching
    public boolean addWatch(AuctionUser watcher, int auctionId) {
        return watchFacade.addWatch(watcher, auctionId);
    }
}