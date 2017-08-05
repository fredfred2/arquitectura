package com.dvdlibrary.util;

import com.dvdlibrary.model.DVDItem;
import java.util.Comparator;

/**
 *
 * @author D.Geary, C.Horstmann
 */
public class ItemComparator implements Comparator {

    public int compare (Object item1, Object item2) {
        DVDItem d1 = (DVDItem)item1;
        DVDItem d2 = (DVDItem)item2;
        if (d1.getTitle().equalsIgnoreCase(d2.getTitle()) &&
            d1.getYear().equals(d2.getYear()) &&
            d1.getGenre().equalsIgnoreCase(d2.getGenre())) {
            return -1;
        } else {
            return 0;
        }
       
    }
}
