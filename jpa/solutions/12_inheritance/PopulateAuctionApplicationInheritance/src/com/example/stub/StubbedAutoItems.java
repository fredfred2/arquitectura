/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stub;

import com.example.model.Image;
import com.example.model.AutoItem;
import com.example.util.ItemCondition;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author tmcginn
 */
public class StubbedAutoItems {

    private static ConcurrentHashMap<Integer, AutoItem> items;
    private static int itemId = 150;
    private static final StubbedAutoItems instance =
            new StubbedAutoItems();

    private StubbedAutoItems() {
        //ConcurrentHashMap<Integer, Image> images = StubbedImages.getImages();
        // AutoItem(int itemID, int imageID, String title, String description, String condition)
        items = new ConcurrentHashMap<>();
        int id = getNextId();
        
        // Just one AutoItem as a test
        items.put(id, new AutoItem(id, null, "1970 Plymouth Gold Duster", "A real beauty! Original 318 V8, gold stripes on sides and rear, wall-to-wall carpeting, pleated, all-vinyl seats, whitewalls, wheel covers, a deluxe insulation package, and a canopy vinyl roof.", ItemCondition.USED, "123VAWE4567", 89036));

    }

    public static ConcurrentHashMap<Integer, AutoItem> getAutoItems() {
        return items;
    }

    public static int getNextId() {

        return itemId++;
    }
}