package com.example.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class AuctionUser implements Serializable {

    //private int userId;
    @Id
    @Column(name="USERID")
    private String displayName;
    private String password;
    private String email;
    private String street;
    private String cityState;
    private String zip;
    // added to the class
    @OneToMany(mappedBy = "bidder")
    Collection<Bid> bids;
    // added to the class
    // Watchers
    @ManyToMany(mappedBy = "watchers")
    private Collection<Auction> auctions;

    public AuctionUser() {
    }

    public AuctionUser(String displayName, String password, String email, String street, String cityState, String zip) {
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.street = street;
        this.cityState = cityState;
        this.zip = zip;
    }

//    public AuctionUser(String displayName, String password, String eMail) {
//        //this.userId = userId;
//        this.displayName = displayName;
//        this.password = password;
//        this.email = eMail;
//    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

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

    @Override
    public String toString() {
        return "AuctionUser{" + "displayName=" + displayName + ", password=" + password + ", email=" + email + ", street=" + street + ", cityState=" + cityState + ", zip=" + zip + ", bids=" + bids + ", auctions=" + auctions + '}';
    }
}