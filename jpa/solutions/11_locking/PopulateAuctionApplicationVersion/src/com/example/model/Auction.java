package com.example.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
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
import javax.persistence.Version;

@Entity
public class Auction implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int auctionId;
    @ManyToOne
    @JoinColumn(name="SELLERID")
    private AuctionUser seller;
    @OneToOne
    @JoinColumn(name="ITEMID")
    private Item item;
    private float currPrice;
    private float increment;
    private int status;  // 1 = active, 2 = ended, 3 = cancelled
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Version
    private int version;
    
    // added to the class
    @OneToMany(mappedBy="auction")
    private Collection<Bid> bids;
    
    // added to the class
    @ManyToMany
    @JoinTable(name="WATCHEDAUCTIONS",
            joinColumns=@JoinColumn(name="AUCTIONID"),
            inverseJoinColumns=@JoinColumn(name="USERID"))
    private Collection<AuctionUser> watchers;

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
    
    // added to the class
    public int getNumWatches() {
        return watchers.size();
    }
    
    @Override
    public String toString() {
        return "Auction{" + "auctionId=" + auctionId + ", seller=" + seller + ", item=" + item + ", currPrice=" + currPrice + ", increment=" + increment + ", status=" + status + ", endDate=" + endDate + '}';
    }
}