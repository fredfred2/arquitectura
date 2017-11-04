package com.example.model;

import java.util.Date;

public class Auction {

    private int auctionId;
    private AuctionUser seller;
    private Item item;
    private float currPrice;
    private float increment;
    private int status;  // 1 = active, 2 = ended, 3 = cancelled
    private Date endDate;

    public Auction() {
    }

    public Auction(int auctionId, AuctionUser seller, Item item, float currPrice, float increment, int status, Date endDate) {
        this.auctionId = auctionId;
        this.seller = seller;
        this.item = item;
        this.currPrice = currPrice;
        this.increment = increment;
        this.status = status;
        this.endDate = endDate;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public AuctionUser getSeller() {
        return seller;
    }

    public void setSeller(AuctionUser seller) {
        this.seller = seller;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(float currPrice) {
        this.currPrice = currPrice;
    }

    public float getIncrement() {
        return increment;
    }

    public void setIncrement(float increment) {
        this.increment = increment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}