package com.example.util;

import com.example.entities.Item;
import java.util.Comparator;

/**
 *
 * @author D.Geary, C.Horstmann
 */
public class ItemComparator implements Comparator {

    public int compare (Object item1, Object item2) {
        Item d1 = (Item)item1;
       Item d2 = (Item)item2;
        if (d1.getTitle().equalsIgnoreCase(d2.getTitle()) &&
            d1.getReleaseyear().equals(d2.getReleaseyear()) &&
            d1.getGenre().equalsIgnoreCase(d2.getGenre())) {
            return -1;
        } else {
            return 0;
        }
       
    }
}
