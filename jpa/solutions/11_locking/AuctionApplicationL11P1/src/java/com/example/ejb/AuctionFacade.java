package com.example.ejb;

import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.AuctionUser_;
import com.example.model.Auction_;
import com.example.model.Item;
import com.example.model.Item_;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Auction> cq = cb.createQuery(Auction.class);
        Root<Auction> auction = cq.from(Auction.class);  // FROM Auction a
        Expression<String> path = auction.get(Auction_.item).get(Item_.title); // a.item.title
        Expression<String> upper = cb.upper(path);  // UPPER(a.item.title)
        cq.select(auction).where(cb.like(upper, searchStr)); // SELECT a FROM Auction a LIKE UPPER(a.item.title) = searchStr
        TypedQuery<Auction> searchQuery = em.createQuery(cq);
        List<Auction> auctions = searchQuery.getResultList();
        auctionList = auctions.toArray(new Auction[0]);
        return buildAuctionListView(auctionList);
    }

    private AuctionUser getUserByName(String userName) {
        AuctionUser user = null;
        if (userName != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<AuctionUser> cq = cb.createQuery(AuctionUser.class);
            Root<AuctionUser> auctionUser = cq.from(AuctionUser.class);
            cq.select(auctionUser).where(cb.equal(auctionUser.get(AuctionUser_.displayName), userName));  // SELECT au FROM AuctionUser au WHERE au.displayName = username
            TypedQuery<AuctionUser> findAuctionUser = em.createQuery(cq);
            user = findAuctionUser.getSingleResult();
        }
        return user;
    }

    // new for L9
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Auction> cq = cb.createQuery(Auction.class);
            Root<Auction> auction = cq.from(Auction.class);  // FROM Auction a

            cq.select(auction); // SELECT a FROM Auction a
            cq.orderBy(cb.asc(auction.get(Auction_.endDate)), cb.asc(auction.get(Auction_.currPrice)));  // ORDER BY a.endDate ASC, a.currPrice ASC
            TypedQuery<Auction> query = em.createQuery(cq);
            List<Auction> auctions = query.getResultList();
            auctionList = auctions.toArray(new Auction[0]);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }

        return buildAuctionListView(auctionList);
    }

    public AuctionListView[] getAllAuctionListView(AuctionStatus status, ItemCondition condition) {
        Auction[] auctionList = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Auction> cq = cb.createQuery(Auction.class);
            Root<Auction> auction = cq.from(Auction.class);  // FROM Auction a

            Predicate criteria = cb.conjunction();
            ParameterExpression<AuctionStatus> paramStatus = cb.parameter(AuctionStatus.class, "status");  // :status
            criteria = cb.equal(auction.get(Auction_.status), paramStatus); // a.status = :status

            if (condition != null) {
                ParameterExpression<ItemCondition> paramCond = cb.parameter(ItemCondition.class, "condition");  // :condition
                Expression path = auction.get(Auction_.item).get(Item_.condition);
                criteria = cb.and(criteria, cb.equal(path, paramCond));  // AND a.item.condition = :condition
            }
            cq.select(auction).where(criteria);
            cq.orderBy(cb.asc(auction.get(Auction_.endDate)), cb.asc(auction.get(Auction_.currPrice)));  // ORDER BY a.endDate ASC, a.currPrice ASC
            TypedQuery auctionQuery = em.createQuery(cq);
            auctionQuery.setParameter("status", status);
            if (condition != null) {
                auctionQuery.setParameter("condition", condition);
            }
            List<Auction> auctions = auctionQuery.getResultList();
            auctionList = auctions.toArray(new Auction[0]);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }

        return buildAuctionListView(auctionList);
    }

    public AuctionListView[] getAllAuctionListView(String userName, AuctionStatus status, ItemCondition condition) {
        Auction[] auctionList = null;
        Query auctionQuery;
        try {
            AuctionUser seller = getUserByName(userName);
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Auction> cq = cb.createQuery(Auction.class);
            Root<Auction> auction = cq.from(Auction.class);  // FROM Auction a

            Predicate criteria = cb.conjunction();
            ParameterExpression<AuctionUser> paramUser = cb.parameter(AuctionUser.class, "seller");  // :seller
            criteria = cb.equal(auction.get(Auction_.seller), paramUser); // a.seller = :seller

            ParameterExpression<AuctionStatus> paramStatus = cb.parameter(AuctionStatus.class, "status");  // :status
            criteria = cb.and(criteria, cb.equal(auction.get(Auction_.status), paramStatus)); // AND a.status = :status

            if (condition != null) {
                ParameterExpression<ItemCondition> paramCond = cb.parameter(ItemCondition.class, "condition");  // :condition
                Expression path = auction.<Auction>get("item").<Item>get("condition");
                criteria = cb.and(criteria, cb.equal(path, paramCond));  // AND a.item.condition = :condition
            }
            cq.select(auction).where(criteria);
            cq.orderBy(cb.asc(auction.get(Auction_.endDate)), cb.asc(auction.get(Auction_.currPrice)));  // ORDER BY a.endDate ASC, a.currPrice ASC
            auctionQuery = em.createQuery(cq);

            auctionQuery.setParameter("seller", seller);  // set seller parameter
            auctionQuery.setParameter("status", status);  // set status

            if (condition != null) {
                auctionQuery.setParameter("condition", condition);  // set condition
            }
            List<Auction> auctions = auctionQuery.getResultList();
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