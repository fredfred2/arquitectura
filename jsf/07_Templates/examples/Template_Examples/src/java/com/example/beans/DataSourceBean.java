/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author tmcginn
 */
@Named("data")
@SessionScoped
public class DataSourceBean implements Serializable {

    private ArrayList<Customer> customerData;

    /** Creates a new instance of DataSource */
    public DataSourceBean() {
        customerData = new ArrayList<Customer> ();
        customerData.add (new Customer ("1123", "Frederickson", "Timothy"));
        customerData.add (new Customer ("1176", "Firenze", "Harry"));
        customerData.add (new Customer ("0275", "Potter", "James"));
        customerData.add (new Customer ("2375", "Zootman", "Peter"));
        customerData.add (new Customer ("0978", "Abornal", "Isaac"));
        customerData.add (new Customer ("1234", "McGinn", "Tom"));
        customerData.add (new Customer ("7806", "Clemens", "Samuel"));
        customerData.add (new Customer ("2396", "Smith", "Robert"));
        customerData.add (new Customer ("2001", "Kubrick", "Stanley"));
        customerData.add (new Customer ("9100", "Ditra", "Shankar"));
    }

    public ArrayList<Customer> getCustomerData() {
        return customerData;
    }

}
