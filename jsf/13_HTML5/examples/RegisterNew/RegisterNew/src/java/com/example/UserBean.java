/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author anshenoy
 */
@Named
@RequestScoped
public class UserBean {

    private String lastName;
    
private String firstName;
private String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
}
