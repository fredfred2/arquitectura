package com.example.stub;

import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Bid;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class StubbedBids {

    // Map: BidId, Bid
    private static ConcurrentHashMap<Integer, Bid> bids;
    private static int bidId = 10;
    private static final StubbedBids instance =
            new StubbedBids();

    private StubbedBids() {
        // Bid(int bidId, int bidderId, int auctionId, float amount, Date bidTime)
        bids = new ConcurrentHashMap<>();
        ConcurrentHashMap<Integer, Auction> auctions = StubbedAuctions.getAuctions();
        int auctionId = 100;  // iPhone
        Auction auction = auctions.get(auctionId);
        int bidderId = 5001; // mheimer
        ConcurrentHashMap<String, AuctionUser> users = StubbedAuctionUsers.getAuctionUsers();
        AuctionUser bidder = users.get("mheimer");
        bids.put(bidId, new Bid(bidId, bidder, auction, 20.99f, new Date()));
        bidId++;
        bids.put(bidId, new Bid(bidId, bidder, auction, 34.99f, new Date()));
        bidId++;
        bids.put(bidId, new Bid(bidId, bidder, auction, 50.49f, new Date()));
        bidId++;
 
    }

    public static ConcurrentHashMap<Integer, Bid> getBids() {
        return bids;
    }

    public static int getNextId() {
        return bidId++;
    }
}
