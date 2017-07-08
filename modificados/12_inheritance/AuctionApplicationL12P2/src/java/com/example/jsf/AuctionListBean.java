package com.example.jsf;

import com.example.ejb.AuctionFacade;
import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Bid;
import com.example.util.AuctionListView;
import com.example.util.AuctionStatus;
import com.example.util.AuctionUtil;
import com.example.util.ItemCondition;
import com.example.util.JSFMessage;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;

// AuctionListBean handles interaction with the full list of auctions, and
// details
//
@Named(value = "list")
@SessionScoped
public class AuctionListBean implements Serializable {

    @Inject
    private AuctionFacade auctionFacade;
    @Inject
    private AuctionManagerBean manager;
    private AuctionListView auctionItem;
    private float bidAmount;
    private boolean listAll = true;
    private boolean watchesOnly = false;
    private String listHeader = "All Auctions";
    private boolean showAll = true;
    private boolean showFilter = false;
    private AuctionStatus auctionStatus = AuctionStatus.ACTIVE;
    private String itemCondition = "All";
    private String[] auctionStatusStrings;
    private String searchStr;
    private Auction auction;

    public AuctionListBean() {
    }

    public AuctionListView[] getAllItems() {
        searchStr = null;
        String userName = manager.getUser().getDisplayName();
        AuctionListView[] list;
        ItemCondition condition = null;
        if (!itemCondition.equals("All")) {
            condition = ItemCondition.valueOf(itemCondition);
        }
        if (userName != null && userName.equals("admin")) {
            list = auctionFacade.getAllAuctionListView();
        } else if (watchesOnly) {
            list = auctionFacade.getAllWatchListView(userName);
        } else if (!listAll) {
            list = auctionFacade.getAllAuctionListView(userName, auctionStatus, condition);
        } else {
            list = auctionFacade.getAllAuctionListView(auctionStatus, condition);
        }
        return list;
    }

    public AuctionListView[] getSearchItems() {
        AuctionListView[] list = null;
        if (searchStr != null && !searchStr.equals("")) {
            list = auctionFacade.getSearchListView(searchStr);
        }
        return list;
    }

    public String prepareDetailView(AuctionListView auctionItem) {
        this.auctionItem = auctionItem;
        bidAmount = auctionItem.getBidPrice();
        auction = auctionFacade.findAuctionById(auctionItem.getAuctionId());
        return "detail";
    }

    public AuctionListView getAuctionItem() {
        return auctionItem;
    }

    public void setAuctionItem(AuctionListView auctionItem) {
        this.auctionItem = auctionItem;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }

    public AuctionManagerBean getManager() {
        return manager;
    }

    public String getCurrBid() {
        String result = "Login or sign-up to bid";
        if (manager.isLoggedIn()) {
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            nf.setMinimumFractionDigits(2);
            String minBid = nf.format((double) auctionItem.getBidPrice());
            result = "enter " + minBid + " or more";
        }
        return result;
    }

    /**
     * Add a Bid entity to an an existing Auction entity. Returns a String, but
     * always null so that we stay on the current page.
     *
     *
     * @return null
     */
    public String enterBid() {
        // Get the current Auction id
        int auctionId = auctionItem.getAuctionId();
        // Check that seller and bidder are not the same
        String sellerName = auction.getSeller().getDisplayName();
        String bidderName = manager.getUser().getDisplayName();
        if (sellerName.equals(bidderName)) {
            JSFMessage.addErrorMessage("You can not bid on your own auction!");
            // Check that the amount enterd to bid is more than the minimum bid price
        } else if (bidAmount < auctionItem.getBidPrice()) {
            JSFMessage.addErrorMessage("You must " + getCurrBid());
        } else {
            // Added L11
            try {
                // Get the data needed to build the Bid entity
                AuctionUser bidder = manager.getUser();
                Bid bid = new Bid();
                bid.setAmount(bidAmount);
                bid.setBidTime(new Date());
                bid.setBidder(bidder);
                bid.setAuction(auction);
                auction.addBid(bid);

                // Set the current price to the bid amount
                auction.setCurrPrice(bidAmount);
                auction.setIncrement(AuctionUtil.defineIncrement(bidAmount));

                // Update the auction - add the bid to it
                auctionFacade.updateAuction(auction);

                // update the auction from the database (version)
                auction = auctionFacade.findAuctionById(auctionId);
                
                // Update the data displayed
                auctionItem.setNumBids(auctionItem.getNumBids() + 1);
                auctionItem.setCurrPrice(bidAmount);
                float incr = AuctionUtil.defineIncrement(bidAmount);
                bidAmount += incr;
                auctionItem.setBidPrice(bidAmount);

            } catch (EJBException ex) {
                Throwable t = ex;
                boolean optimisticLockException = false;
                while ((t = t.getCause()) != null) {
                    if (t instanceof OptimisticLockException) {
                        optimisticLockException = true;
                        break;
                    }
                }
                if (optimisticLockException) {
                    JSFMessage.addWarnMessage("You have been outbid - try again!");
                } else {
                    JSFMessage.addErrorMessage("EJBException adding bid: " + ex.getMessage());
                }
                refreshAuctionView(auctionId);
                return null;
            }
            JSFMessage.addInfoMessage("Congratulations! You are the high biddder!");
        }
        return null;
    }

    // Refresh the data in this auction view
    private void refreshAuctionView(int auctionId) {
        auction = auctionFacade.findAuctionById(auctionId);
        auctionItem.setCurrPrice(auction.getCurrPrice());
        float incr = AuctionUtil.defineIncrement(auction.getCurrPrice());
        bidAmount = auction.getCurrPrice() + incr;
        auctionItem.setBidPrice(bidAmount);
        auctionItem.setNumBids(auction.getNumBids());
    }

    public void addWatch() {
        int auctionId = auctionItem.getAuctionId();
        AuctionUser watcher = manager.getUser();
        if (auctionFacade.addWatch(watcher, auctionId)) {
            JSFMessage.addInfoMessage("You are now watching this auction item");
        } else {
            JSFMessage.addErrorMessage("You are already watching this auction item!");
        }
    }

    public void removeWatch(int auctionId) {
        AuctionUser watcher = manager.getUser();
        if (auctionFacade.removeWatch(watcher, auctionId)) {
            JSFMessage.addInfoMessage("You are no longer watching this auction item");
        } else {
            JSFMessage.addErrorMessage("You are not watching this auction item!");
        }
    }

    public void removeAuction(int auctionId) {
        String title = auctionFacade.findAuctionById(auctionId).getItem().getTitle();
        if (auctionFacade.removeAuction(auctionId)) {
            JSFMessage.addInfoMessage("Removed Auction: " + title);
        } else {
            JSFMessage.addErrorMessage("Unable to remove Auction: " + title);
        }
    }

    public void cancelAuction(int auctionId) {
        String title = auctionFacade.findAuctionById(auctionId).getItem().getTitle();
        if (auctionFacade.cancelAuction(auctionId)) {
            JSFMessage.addInfoMessage("Canceled Auction: " + title);
        } else {
            JSFMessage.addErrorMessage("Unable to cancel Auction: " + title);
        }
    }

    public void endAuction(int auctionId) {
        String title = auctionFacade.findAuctionById(auctionId).getItem().getTitle();
        if (auctionFacade.endAuction(auctionId)) {
            JSFMessage.addInfoMessage("Ended Auction: " + title);
        } else {
            JSFMessage.addErrorMessage("Unable to end Auction: " + title);
        }
    }

    public boolean isListAll() {
        return listAll;
    }

    public void setListAll(boolean listAll) {
        this.listAll = listAll;
    }

    public boolean isWatchesOnly() {
        return watchesOnly;
    }

    public void setWatchesOnly(boolean watchesOnly) {
        this.watchesOnly = watchesOnly;
    }

    public String getListHeader() {
        return listHeader;
    }

    public void setListHeader(String listHeader) {
        this.listHeader = listHeader;
    }

    public boolean isShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    public int getAuctionStatus() {
        return auctionStatus.ordinal();
    }

    public void setAuctionStatus(int auctionStatus) {
        this.auctionStatus = AuctionStatus.values()[auctionStatus];
    }

    public String[] getAuctionStatusStrings() {
        return auctionStatusStrings;
    }

    public void setAuctionStatusStrings(String[] auctionStatusStrings) {
        this.auctionStatusStrings = auctionStatusStrings;
    }

    public boolean isShowFilter() {
        return showFilter;
    }

    public void toggleShowFilter() {
        if (showFilter) {
            showFilter = false;
        } else {
            showFilter = true;
        }
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }
}