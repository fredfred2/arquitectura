/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.java.util;

import com.java.sample.Customer;
import java.util.Comparator;


public class ItemComparator implements Comparator {

    public int compare (Object item1, Object item2) {
        Customer d1 = (Customer)item1;
        Customer d2 = (Customer)item2;
        if (d1.getCustomerID().equalsIgnoreCase(d2.getCustomerID()) &&
            d1.getLastName().equals(d2.getLastName()) &&
            d1.getFirstName().equalsIgnoreCase(d2.getFirstName())) {
            return -1;
        } else {
            return 0;
        }
       
    }
}
