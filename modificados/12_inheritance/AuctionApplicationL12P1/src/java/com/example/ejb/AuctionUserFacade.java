package com.example.ejb;

import com.example.model.AuctionUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuctionUserFacade {

    @PersistenceContext(unitName = "AuctionPU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(AuctionUserFacade.class.getName());

    public AuctionUserFacade() {
    }

    // Return true if this is a valid AuctionUser
    public AuctionUser isValidAuctionUser(String displayName, String password) {
        AuctionUser checkUser = null;
        try {
            checkUser = em.find(AuctionUser.class, displayName);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        if (checkUser != null) {
            if (!checkUser.getPassword().equals(password)) {
                checkUser = null;  // displayName user exists and passwords match
            }
        }
        return checkUser;
    }

    // Return true if the add was successful - note the user must be new
    public void addNewAuctionUser(AuctionUser user) throws EJBException {
        if (em.find(AuctionUser.class, user.getDisplayName()) != null) {
            throw new EJBException("The user name " + user.getDisplayName() + " is already taken");
        }
        em.persist(user);
    }
}