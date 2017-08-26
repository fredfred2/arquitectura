/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author anshenoy
 */
@Named
@RequestScoped
public class UserBean {

    private String lastName;

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
