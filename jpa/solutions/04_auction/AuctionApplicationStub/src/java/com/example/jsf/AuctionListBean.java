package com.example.jsf;

import com.example.ejb.AuctionFacade;
import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.util.AuctionListView;
import com.example.util.AuctionUtil;
import com.example.util.JSFMessage;
import java.io.Serializable;
import java.text.NumberFormat;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

// AuctionListBean handles interaction with the full list of auctions, and
// details
//
@Named(value = "list")
@SessionScoped
public class AuctionListBean implements Serializable {

    @Inject
    private AuctionFacade auctionControl;
    @Inject
    private AuctionManagerBean manager;
    private AuctionListView auctionItem;
    private float bidAmount;

    public AuctionListBean() {
    }

    public AuctionListView[] getAllItems() {
        return auctionControl.getAllAuctionListView();
    }

    public String prepareDetailView(AuctionListView auctionItem) {
        this.auctionItem = auctionItem;
        bidAmount = auctionItem.getBidPrice();
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

    public void enterBid() {
        int auctionId = auctionItem.getAuctionId();
        Auction auction = auctionControl.findAuctionById(auctionId);
        String sellerName = auction.getSeller().getDisplayName();
        String bidderName = manager.getUser().getDisplayName();
        if (sellerName.equals(bidderName)) {
            JSFMessage.addErrorMessage("You can not bid on your own auction!");
        } else if (bidAmount < auctionItem.getBidPrice()) {
            JSFMessage.addErrorMessage("You must " + getCurrBid());
        } else {
            // Send the data to the controller
            AuctionUser bidder = manager.getUser();
            auctionControl.addBid(bidder, auction, bidAmount);
            // Update the data displayed
            auctionItem.setNumBids(auctionItem.getNumBids() + 1);
            auctionItem.setCurrPrice(bidAmount);
            float incr = AuctionUtil.defineIncrement(bidAmount);
            bidAmount += incr;
            auctionItem.setBidPrice(bidAmount);
            JSFMessage.addInfoMessage("Congratulations! You are the high biddder!");
        }
    }

    public void addWatch() {
        int auctionId = auctionItem.getAuctionId();
        AuctionUser watcher = manager.getUser();
        if (auctionControl.addWatch(watcher, auctionId)) {
            JSFMessage.addInfoMessage("You are now watching this auction item");
        } else {
            JSFMessage.addErrorMessage("You are already watching this auction item!");
        }
    }
    
    
    public void cancelAuction(int auctionId) {
        String title = auctionControl.findAuctionById(auctionId).getItem().getTitle();
        if (auctionControl.cancelAuction(auctionId)) {
            JSFMessage.addInfoMessage("Canceled Auction: " + title);
        } else {
            JSFMessage.addErrorMessage("Unable to cancel Auction: " + title);
        }
    }

    public void endAuction(int auctionId) {
        String title = auctionControl.findAuctionById(auctionId).getItem().getTitle();
        if (auctionControl.endAuction(auctionId)) {
            JSFMessage.addInfoMessage("Ended Auction: " + title);
        } else {
            JSFMessage.addErrorMessage("Unable to end Auction: " + title);
        }
    }

    public void removeAuction(int auctionId) {
        String title = auctionControl.findAuctionById(auctionId).getItem().getTitle();
        if (auctionControl.removeAuction(auctionId)) {
            JSFMessage.addInfoMessage("Removed Auction: " + title);
        } else {
            JSFMessage.addErrorMessage("Unable to remove Auction: " + title);
        }

    }
}
