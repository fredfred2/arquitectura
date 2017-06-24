package com.example.ejb;

import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Item;
import com.example.util.AuctionEndedException;
import com.example.util.AuctionListView;
import com.example.util.AuctionStatus;
import com.example.util.AuctionUtil;
import com.example.util.ItemCondition;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class AuctionFacade {

    @PersistenceContext(unitName = "AuctionPU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(AuctionFacade.class.getName());
    private AuctionListView[] auctionItemsListed;

    public AuctionFacade() {
    }

    public AuctionListView[] getSearchListView(String searchStr) {
        searchStr = "%" + searchStr.toUpperCase() + "%";
        Auction[] auctionList;
        TypedQuery<Auction> searchQuery = em.createNamedQuery("Auction.search", Auction.class);
        searchQuery.setParameter("search", searchStr);
        List<Auction> auctions = searchQuery.getResultList();
        auctionList = auctions.toArray(new Auction[0]);
        return buildAuctionListView(auctionList);
    }

    private AuctionUser getUserByName(String userName) {
        AuctionUser user = null;
        if (userName != null) {
            TypedQuery<AuctionUser> findAuctionUser = em.createNamedQuery("AuctionUser.findByUserName", AuctionUser.class);
            findAuctionUser.setParameter("displayName", userName);
            user = findAuctionUser.getSingleResult();
        }
        return user;
    }

    // new for L8
    public AuctionListView[] getAllWatchListView(String userName) {
        Auction[] auctionList = null;
        try {
            AuctionUser watcher = getUserByName(userName);
            auctionList = watcher.getAuctions().toArray(new Auction[0]);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        
        return buildAuctionListView(auctionList);
    }

    public AuctionListView[] getAllAuctionListView() {
        Auction[] auctionList = null;
        try {
            TypedQuery<Auction> auctionQuery = em.createNamedQuery("Auction.findAll", Auction.class);
            List<Auction> auctions = auctionQuery.getResultList();
            auctionList = auctions.toArray(new Auction[0]);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }

        return buildAuctionListView(auctionList);
    }

    public AuctionListView[] getAllAuctionListView(AuctionStatus status, ItemCondition condition) {
        Auction[] auctionList = null;
        try {
            TypedQuery<Auction> query;     
            if (condition == null) {
                query = em.createNamedQuery("Auction.findByStatus", Auction.class);
                query.setParameter("status", status);
            } else {
                query = em.createNamedQuery("Auction.findByStatusbyCondition", Auction.class);
                query.setParameter("status", status);
                query.setParameter("condition", condition);
            }
            List<Auction> auctions = query.getResultList();
            auctionList = auctions.toArray(new Auction[0]);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }

        return buildAuctionListView(auctionList);
    }

    public AuctionListView[] getAllAuctionListView(String userName, AuctionStatus status, ItemCondition condition) {
        Auction[] auctionList = null;
        try {
            AuctionUser seller = getUserByName(userName);
            TypedQuery<Auction> query;      // added L8
            if (condition == null) {
                query = em.createNamedQuery("Auction.findBySellerByStatus", Auction.class);
                query.setParameter("seller", seller);
                query.setParameter("status", status);
            } else {
                query = em.createNamedQuery("Auction.findBySellerByStatusByCondition", Auction.class);
                query.setParameter("seller", seller);
                query.setParameter("status", status);
                query.setParameter("condition", condition);
            }
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

    // Update an auction - for example, to add a bid
    public void updateAuction(Auction auction) throws EJBException {
        try {
            int auctionId = auction.getAuctionId();
            Auction managedAuction = em.find(Auction.class, auctionId);
            em.merge(auction);
        } catch (EJBException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new EJBException(e);
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

    public boolean removeWatch(AuctionUser watcher, int auctionId) {
        boolean result = false;
        Auction auction;
        try {
            auction = em.find(Auction.class, auctionId);
            if (auction.isWatchedBy(watcher)) {
                auction.removeWatcher(watcher);
                em.merge(auction);
                result = true;
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return result;
    }
}