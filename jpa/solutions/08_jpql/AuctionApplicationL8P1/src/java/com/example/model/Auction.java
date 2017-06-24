package com.example.model;

import com.example.util.AuctionStatus;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Auction implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int auctionId;
    @ManyToOne
    @JoinColumn(name = "SELLERID")
    private AuctionUser seller;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "ITEMID")
    private Item item;
    private float currPrice;
    private float increment;
    @Enumerated(EnumType.ORDINAL)  // Stored in the datbase as an int
    private AuctionStatus status = AuctionStatus.ACTIVE;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    // added to the class
    @OneToMany(mappedBy = "auction")
    private Collection<Bid> bids;
    // added to the class
    @ManyToMany
    @JoinTable(name = "WATCHEDAUCTIONS",
    joinColumns =
    @JoinColumn(name = "AUCTIONID"),
    inverseJoinColumns =
    @JoinColumn(name = "WATCHERID"))
    private Collection<AuctionUser> watchers;

    public Auction() {
    }

    public Auction(int auctionId, AuctionUser seller, Item item, float currPrice, float increment, AuctionStatus status, Date endDate) {
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

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = AuctionStatus.values()[status];
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // added to the class
    public void addBid(Bid bid) {
        bids.add(bid);
    }

    // added to the class
    public int getNumBids() {
        return bids.size();
    }

    // added to the class
    public void addWatcher(AuctionUser watcher) {
        watchers.add(watcher);
    }

    public boolean isWatchedBy(AuctionUser watcher) {
        return watchers.contains(watcher);
    }

    public void removeWatcher(AuctionUser watcher) {
        watchers.remove(watcher);
    }

    // added to the class
    public int getNumWatches() {
        return watchers.size();
    }
}