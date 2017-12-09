/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stub;

import com.example.model.Auction;
import com.example.model.AuctionUser;
import com.example.model.Item;
import com.example.util.AuctionStatus;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create an in-memory set of Auction instances for use in the
 * AuctionApplication
 *
 * @author tmcginn
 */
public class StubbedAuctions {
    // Use displayName as the key

    private static ConcurrentHashMap<Integer, Auction> auctions;
    private static Auction auction;
    private static ConcurrentHashMap<String, AuctionUser> users;
    private static AuctionUser seller;
    private static ConcurrentHashMap<Integer, Item> items;
    private static Item item;
    private static int auctionID = 100;
    private static final StubbedAuctions instance =
            new StubbedAuctions();

    private StubbedAuctions() {
        // Auction(int auctionID, int sellerID, int itemID, float currPrice, float increment, int status, Date endDate)
        auctions = new ConcurrentHashMap<>();
        items = StubbedItems.getItems();
        users = StubbedAuctionUsers.getAuctionUsers();

        // All Auctions have the same start and end date (roughly)
        Date startDate = new GregorianCalendar().getTime();
        GregorianCalendar future = new GregorianCalendar();
        future.add(Calendar.DAY_OF_MONTH, 7);
        Date endDate = future.getTime();
        AuctionStatus status = AuctionStatus.ACTIVE;

        // Create a set of Auctions
        //
        // Get an item
        item = items.get(100); // Phone stand
        // Get a user
        seller = users.get("cchurch");
        // Create the auction
        float startPrice = 10.49f;
        float increment = 1.00f;
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);

        // Get an item
        item = items.get(101); // American Girl
        // Get a user
        seller = users.get("tmcginn");
        // Create the auction
        startPrice = 0.99f;
        increment = 1.00f;
//        future.add(Calendar.DAY_OF_MONTH, 5);
//        endDate = future.getTime();
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);

        // Get an item
        item = items.get(102); // Coffe grinder
        // Get a user
        seller = users.get("cchurch");
        // Create the auction
        startPrice = 51.00f;
        increment = 10.00f;
//        future.add(Calendar.DAY_OF_MONTH, 3);
//        endDate = future.getTime();
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);

        // Get an item
        item = items.get(103); // Salt and Pepper
        // Get a user
        seller = users.get("mheimer");
        // Create the auction
        startPrice = 1.00f;
        increment = 1.00f;
//        future.add(Calendar.DAY_OF_MONTH, 2);
//        endDate = future.getTime();
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);

        // Get an item
        item = items.get(104); // Lil'Kinz
        // Get a user
        seller = users.get("tmcginn");
        // Create the auction
        startPrice = 9.99f;
        increment = 1.00f;
//        future.add(Calendar.DAY_OF_MONTH, 7);
//        endDate = future.getTime();
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);

        // Get an item
        item = items.get(105); // North Face Backpack
        // Get a user
        seller = users.get("pfernandez");
        // Create the auction
        startPrice = 9.99f;
        increment = 1.00f;
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);

        // Get an item
        item = items.get(106); // Horse
        // Get a user
        seller = users.get("cchurch");
        // Create the auction
        startPrice = 19.99f;
        increment = 1.00f;
//        future.add(Calendar.DAY_OF_MONTH, 3);
//        endDate = future.getTime();
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);

        // Get an item
        item = items.get(107); // Hp Printer
        // Get a user
        seller = users.get("mheimer");
        // Create the auction
        startPrice = 9.99f;
        increment = 1.00f;
//        future.add(Calendar.DAY_OF_MONTH, 2);
//        endDate = future.getTime();
        auction = new Auction(auctionID, seller, item, startPrice, increment, status, endDate);
        auctions.put(StubbedAuctions.getNextId(), auction);
    }

    /**
     * Returns the current in-memory ConcurrentHashMap of Auction instances.
     *
     * @return ConcurrentHashMap<String, AuctionUser> users
     */
    public static ConcurrentHashMap<Integer, Auction> getAuctions() {
        return auctions;
    }

    /**
     * Returns the next Id value
     *
     * @return int
     */
    private static int getNextId() {
        return auctionID++;
    }
}