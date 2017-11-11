package com.example.jsf;

import com.example.ejb.AuctionUserFacade;
import com.example.model.AuctionUser;
import com.example.util.JSFMessage;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Named(value = "manager")
@SessionScoped
public class AuctionManagerBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(AuctionManagerBean.class.getName());
    private AuctionUser user;
    private boolean loggedIn = false;
    private String passCheck, checkPassword;
    // AuctionUser data
    private String displayName;
    private String password;
    private String email;
    private String street;
    private String cityState;
    private String zip;
    @Inject
    private AuctionUserFacade userFacade;

    public AuctionManagerBean() {
    }

    public AuctionUser getUser() {
        return user;
    }

    public void setUser(AuctionUser user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String validateLogin() {
        String result = null;
        user = userFacade.isValidAuctionUser(displayName, password);
        if (user != null) {
            loggedIn = true;
            JSFMessage.addInfoMessage("Welcome back to Java Auctions, " + user.getDisplayName());
            result = "home";
        } else {
            JSFMessage.addErrorMessage("Invalid username or password. Try again, or create an account.");
        }
        return result;
    }

    public String logout() {
        loggedIn = false;
        displayName = null;
        password = null;
        init();
        JSFMessage.addInfoMessage("You are now logged out.");
        return "home";
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
        if (this.getPassword() != null && checkPassword != null) {
            if (this.getPassword().equals(checkPassword)) {
                passCheck = null;
            } else {
                passCheck = "Passwords don't match";
            }
        }
    }

    public String getPassCheck() {
        return passCheck;
    }

    public String createUser() {
        String result = null;
        // add this user to the system
        try {
            user = new AuctionUser(displayName, password, email, street, cityState, zip);
            userFacade.addNewAuctionUser(user);
            loggedIn = true;
            checkPassword = null;
            displayName = null;
            password = null;
            email = null;
            street = null;
            cityState = null;
            zip = null;
            JSFMessage.addInfoMessage("Welcome to Java Auctions " + user.getDisplayName() + "! Click on Create an Auction to get started.");
            result = "home";
        } catch (EJBException e) {
            Throwable t = e;
            boolean constraintException = false;
            boolean sqlException = false;
            while ((t = t.getCause()) != null) {
                if (t instanceof ConstraintViolationException) {
                    ConstraintViolationException cve = (ConstraintViolationException) t;
                    Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();
                    ConstraintViolation[] list = violations.toArray(new ConstraintViolation[0]);
                    for (ConstraintViolation cv : list) {
                        JSFMessage.addErrorMessage(cv.getMessage());
                    }
                    constraintException = true;
                    break;
                }
            }
            if (!constraintException) {
                t = e;
                while ((t = t.getCause()) != null) {
                    if (t instanceof SQLIntegrityConstraintViolationException) {
                        JSFMessage.addErrorMessage("The user name " + user.getDisplayName() + " caused a SQL Exception. Likely because the user name is already taken.");
                        sqlException = true;
                    }
                }
            }
            if (!constraintException && !sqlException) {
                JSFMessage.addErrorMessage(e.getMessage());
            }
        }
        return result;
    }

    @PostConstruct
    private void init() {
        user = new AuctionUser();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}