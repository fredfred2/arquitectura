/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author anshenoy
 */
@Named(value = "mainController")
@RequestScoped
public class MainController {

    /**
     * Creates a new instance of MainController
     */
   String lookupId;
    Customer customer = new Customer();
 
    public String getLookupId() {
        return lookupId;
    }
    public void setLookupId(String lookupId) {
        this.lookupId = lookupId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String doLookup() {
        CustomerDAO dao = new CustomerDAO();
 
        customer = dao.findCustomer(lookupId);
 
        return "result";
    }
}
