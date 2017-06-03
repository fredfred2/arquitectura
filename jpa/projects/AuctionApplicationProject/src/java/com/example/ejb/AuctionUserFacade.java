package com.example.ejb;

import com.example.model.AuctionUser;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

@Stateless
public class AuctionUserFacade {
    
    private static final Logger LOG = Logger.getLogger(AuctionUserFacade.class.getName());

    public AuctionUserFacade() {
    }
    
    // Return true if this is a valid AuctionUser
    public AuctionUser isValidAuctionUser(String displayName, String password) {
        AuctionUser checkUser = null;
        // Practice 6-1 code goes here

        return checkUser;
    }

    // Return true if the add was successful - note the user must be new
    public void addNewAuctionUser(AuctionUser user) throws EJBException {
        // Practice 6-1 code goes here

    }
}