package com.example.ejb;

import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Bid;
import com.example.model.Item;
import com.example.util.AuctionEndedException;
import com.example.util.AuctionListView;
import com.example.util.AuctionStatus;
import com.example.util.AuctionUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class AuctionFacade {

    private static final Logger LOG = Logger.getLogger(AuctionFacade.class.getName());
    private AuctionListView[] auctionItemsListed;

    public AuctionFacade() {
    }

    public AuctionListView[] getAllAuctionListView() {
        Auction[] auctionList = null;

        // Practice 6-1 code goes here
        return buildAuctionListView(auctionList);
    }

    private AuctionListView[] buildAuctionListView(Auction[] auctionList) {
        // Get a count to create the array
        if (auctionList != null) {
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
                // Practice 6-1 code fixes here
                int numBids = 0;
                int numWatches = 0;                
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
        }
        return auctionItemsListed;
    }

    public void addAuction(Auction auction) {
        // Practice 6-1 code goes here
    }

    public boolean removeAuction(int auctionId) {
        boolean result = false;
        // Practice 6-1 code goes here

        return result;
    }

    public boolean cancelAuction(int auctionId) {
        boolean result = false;

        return result;
    }

    public boolean endAuction(int auctionId) {
        boolean result = false;

        return result;
    }

    public Auction findAuctionById(int auctionId) {
        Auction auction = null;
        // Practice 6-1 code goes here

        return auction;
    }

    // Insert a new Bid
    public void updateAuction(AuctionUser bidder, Auction auction, float amount) {
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setBidTime(new Date());
        bid.setBidder(bidder);
        bid.setAuction(auction);
        // Practice 6-1 code goes here

    }

    // Return true if add was successful and false if already watching
    public boolean addWatch(AuctionUser watcher, int auctionId) {
        boolean result = false;
        Auction auction;
        // Practice 6-1 code goes here

        return result;
    }
}