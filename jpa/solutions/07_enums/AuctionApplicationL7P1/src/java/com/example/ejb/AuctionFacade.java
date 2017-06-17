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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AuctionFacade {

    @PersistenceContext(unitName = "AuctionPU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(AuctionFacade.class.getName());
    private AuctionListView[] auctionItemsListed;

    public AuctionFacade() {
    }

    public AuctionListView[] getAllAuctionListView() {
        Auction[] auctionList = null;
        try {
            TypedQuery<Auction> query = em.createQuery("SELECT a FROM Auction a", Auction.class);
            List<Auction> auctions = query.getResultList();
            auctionList = auctions.toArray(new Auction[0]);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        
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
                int numBids = a.getNumBids();
                int numWatches = a.getNumWatches();
                float currPrice = a.getCurrPrice();
                float bidPrice = a.getCurrPrice() + a.getIncrement();
                String timeRemaining = null;
                // Convert the integer value to its enum type
                switch (a.getStatus()) {
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
        try {
            em.persist(auction);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }

    public boolean removeAuction(int auctionId) {
        boolean result = false;
        try {
            Auction auction = em.find(Auction.class, auctionId);
            em.remove(auction);
            result = true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return result;
    }

    public boolean cancelAuction(int auctionId) {
        boolean result = false;
        try {
            Auction auction = em.find(Auction.class, auctionId);
            auction.setStatus(AuctionStatus.CANCELLED);
            result = true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return result;
    }

    public boolean endAuction(int auctionId) {
        boolean result = false;
        try {
            Auction auction = em.find(Auction.class, auctionId);
            auction.setStatus(AuctionStatus.ENDED);
            result = true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return result;
    }

    public Auction findAuctionById(int auctionId) {
        Auction auction = null;
        try {
            auction = em.find(Auction.class, auctionId);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return auction;

    }

    // Insert a new Bid
    public void updateAuction(AuctionUser bidder, Auction auction, float amount) {
        Bid bid = new Bid();
        bid.setAmount(amount);
        bid.setBidTime(new Date());
        bid.setBidder(bidder);
        bid.setAuction(auction);
        try {
            int auctionId = auction.getAuctionId();
            auction = em.find(Auction.class, auctionId);

            auction.addBid(bid);

            // Set the current price to the bid amount
            auction.setCurrPrice(amount);
            // See if the increment needs updating
            auction.setIncrement(AuctionUtil.defineIncrement(amount));
            // update the auction
            em.merge(auction);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }

    // Return true if add was successful and false if already watching
    public boolean addWatch(AuctionUser watcher, int auctionId) {
        boolean result = false;
        Auction auction;
        try {
            auction = em.find(Auction.class, auctionId);
            if (!auction.isWatchedBy(watcher)) {
                auction.addWatcher(watcher);
                em.merge(auction);
                result = true;
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return result;
    }
}