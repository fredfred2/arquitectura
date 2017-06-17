package com.example.ejb;

import com.example.model.Bid;
import com.example.stub.StubbedBids;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import javax.ejb.Stateless;

@Stateless
public class BidFacade {

    public void addBid(Bid bid) {
        ConcurrentHashMap<Integer, Bid> bids = StubbedBids.getBids();
        bids.put(bid.getBidId(), bid);
    }

    // With a database you could user a query to return
    // just the rows needed
    // Here, with the map, I have to go through all the elements
    public int numBids(int auctionId) {
        ConcurrentHashMap<Integer, Bid> bids = StubbedBids.getBids();
        int num = 0;
        Bid bid;
        bids = StubbedBids.getBids();
        Iterator<Bid> bidList = bids.values().iterator();
        while (bidList.hasNext()) {
           bid = bidList.next();
           if (bid.getAuction().getAuctionId() == auctionId) {
               num++;
           }
        }
        return num;
    }
}
