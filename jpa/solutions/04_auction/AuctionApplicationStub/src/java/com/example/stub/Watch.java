/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stub;

/**
 *
 * @author tmcginn
 */
public class Watch {
    public String watcherId;
    public int auctionId;

    public Watch(String watcherId, int auctionId) {
        this.watcherId = watcherId;
        this.auctionId = auctionId;
    }

    public String getWatcherId() {
        return watcherId;
    }

    public void setWatcherId(String watcherId) {
        this.watcherId = watcherId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
}
