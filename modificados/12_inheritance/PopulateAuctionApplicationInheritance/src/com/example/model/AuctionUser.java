package com.example.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "AuctionUser.findByUserName", query = "SELECT au FROM AuctionUser au WHERE au.displayName = :displayName")
})
public class AuctionUser implements Serializable {

    @Id
    @Column(name = "USERID")
    private String displayName;
    private String password;
    //@Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Invalid e-mail address")
    private String email;
    @Embedded
    private Address address;
//    private String street;
//    private String cityState;
//    @ZipCode
//    private String zip;
    // added to the class
    @OneToMany(mappedBy = "bidder")
    private Collection<Bid> bids;
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
        address = new Address(street, cityState, zip);
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
        return address.getStreet();
    }

    public void setStreet(String street) {
        address.setStreet(street);
    }

    public String getCityState() {
        return address.getCityState();
    }

    public void setCityState(String cityState) {
        address.setCityState(cityState);
    }

    // Bean Validation moved here L12P1
    public String getZip() {
        return address.getZip();
    }

    public void setZip(String zip) {
        address.setZip(zip);
    }

    public Collection<Bid> getBids() {
        return bids;
    }

    public Collection<Auction> getAuctions() {
        return auctions;
    }
}
