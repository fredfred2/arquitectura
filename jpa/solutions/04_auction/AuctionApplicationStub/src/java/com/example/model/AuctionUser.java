package com.example.model;

import java.util.Objects;

public class AuctionUser {

    private String displayName;
    private String password;
    private String email;
    private String street;
    private String cityState;
    private String zip;

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
        return "AuctionUser{" + "displayName=" + displayName + ", password=" + password + ", email=" + email + ", street=" + street + ", cityState=" + cityState + ", zip=" + zip + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.displayName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuctionUser other = (AuctionUser) obj;
        if (!Objects.equals(this.displayName, other.displayName)) {
            return false;
        }
        return true;
    }
}