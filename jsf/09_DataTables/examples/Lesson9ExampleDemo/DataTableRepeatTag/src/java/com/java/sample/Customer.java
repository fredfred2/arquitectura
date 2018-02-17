/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java.sample;

import java.io.Serializable;


public class Customer implements Serializable {

    private String customerID;
    private String lastName;
    private String firstName;

    public Customer (String ID, String last, String first) {
        customerID = ID;
        lastName = last;
        firstName = first;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
