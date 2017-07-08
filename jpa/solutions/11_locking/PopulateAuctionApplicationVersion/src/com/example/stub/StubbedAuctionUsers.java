package com.example.stub;

import com.example.model.AuctionUser;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create an in-memory set of AuctionUsers instances for use in the AuctionApplication
 * @author tmcginn
 */
public class StubbedAuctionUsers {
    // Use displayName as the key
    private static ConcurrentHashMap<String, AuctionUser> users;
    private static int auctionID = 5000;
    private static final StubbedAuctionUsers instance =
            new StubbedAuctionUsers();

    private StubbedAuctionUsers() {
        // AuctionUser(int userID, String displayName, String password, String eMail)
        users = new ConcurrentHashMap<>();
        users.put("admin", new AuctionUser("admin", "admin", "admin@server", "None", "None", "None"));
        users.put("tmcginn", new AuctionUser("tmcginn", "tmcginn", "tom.mcginn@oracle.com", "100 Main Street", "Anytown, MA", "01803"));  
        users.put("mheimer", new AuctionUser("mheimer", "mheimer","matt.heimer@oracle.com", "100 Washington Street", "Anythown, TX", "45103"));  
        users.put("pfernandez", new AuctionUser("pfernandez", "pfernandez","peter.fernandez@oracle.com", "100 San Pedro Ave", "Anytown, CA", "95014")); 
        users.put("cchurch", new AuctionUser("cchurch", "cchurch", "cindy.church@oracle.com", "101 Main Street", "Anytown, CA", "94012"));
        
    }
    
    /**
     * Returns the current in-memory ConcurrentHashMap of AuctionUser instances.
     * @return ConcurrentHashMap<String, AuctionUser> users
     */
    public static ConcurrentHashMap<String, AuctionUser> getAuctionUsers() {
        return users;
    }
    
}