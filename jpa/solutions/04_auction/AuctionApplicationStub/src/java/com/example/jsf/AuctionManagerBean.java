package com.example.jsf;

import com.example.ejb.AuctionUserFacade;
import com.example.model.AuctionUser;
import com.example.util.JSFMessage;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "manager")
@SessionScoped
public class AuctionManagerBean implements Serializable {

    private AuctionUser user;
    private boolean loggedIn = false;
    private String passCheck, checkPassword;
    @Inject
    private AuctionUserFacade userController;

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
        // TODO: call controller to check this user
        if (userController.isValidAuctionUser(user)) {
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
        init();
        JSFMessage.addInfoMessage("You are now logged out.");
        return "home";
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
        if (user.getPassword() != null && checkPassword != null) {
            if (user.getPassword().equals(checkPassword)) {
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
        System.out.println("Calling createUser");
        String result = null;
        // add this user to the system
        if (userController.addNewAuctionUser(user)) {
            loggedIn = true;
            checkPassword = null;
            JSFMessage.addInfoMessage("Welcome to Java Auctions " + user.getDisplayName() + "! Click on Sell an Item to get started.");
            result = "home";
        } else {
            JSFMessage.addErrorMessage("The user name " + user.getDisplayName() + " is already taken. Please choose another.");
        }
        return result;
    }

    @PostConstruct
    private void init() {
        user = new AuctionUser();
    }
}
