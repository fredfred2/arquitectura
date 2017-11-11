package com.example.ejb;

import com.example.model.AuctionUser;
import com.example.stub.StubbedAuctionUsers;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class AuctionUserFacade {

    private static final Logger LOG = Logger.getLogger(AuctionUserFacade.class.getName());

    public AuctionUserFacade() {
    }

    // Return true if this is a valid AuctionUser
    public boolean isValidAuctionUser(AuctionUser user) {
        ConcurrentHashMap<String, AuctionUser> users = StubbedAuctionUsers.getAuctionUsers();
        boolean result = false;
        AuctionUser checkUser = users.get(user.getDisplayName());
        if (checkUser != null) {
            if (checkUser.getPassword().equals(user.getPassword())) {
                result = true;  // displayName user exists and passwords match
                //user.setUserId(checkUser.getUserId());  // set the user id
            }
        }
        return result;
    }

    // Return true if the add was successful - note the user must be new
    public boolean addNewAuctionUser(AuctionUser user) {
        ConcurrentHashMap<String, AuctionUser> users = StubbedAuctionUsers.getAuctionUsers();
        boolean result = false;
        if (!users.containsKey(user.getDisplayName())) {
            users.put(user.getDisplayName(), user);
            result = true;
        }
        return result;
    }
}