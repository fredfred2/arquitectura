/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

/**
 *
 * @author tmcginn
 */
// Data
public class AuctionListView {
    private int auctionId;
    private int imageId;
    private String title;
    private String description;
    private String condition;
    private int numBids;
    private int numWatches;
    private float currPrice;
    private float bidPrice;
    private String timeRemaining;

    public AuctionListView(int auctionId, int imageId, String title, String description, String condition, int numBids, int numWatches, float currPrice, float bidPrice, String timeRemaining) {
        this.auctionId = auctionId;
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.condition = condition;
        this.numBids = numBids;
        this.numWatches = numWatches;
        this.currPrice = currPrice;
        this.bidPrice = bidPrice;
        this.timeRemaining = timeRemaining;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getNumBids() {
        return numBids;
    }

    public void setNumBids(int numBids) {
        this.numBids = numBids;
    }

    public int getNumWatches() {
        return numWatches;
    }

    public void setNumWatches(int numWatches) {
        this.numWatches = numWatches;
    }

    public float getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(float currPrice) {
        this.currPrice = currPrice;
    }

    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
    
}
