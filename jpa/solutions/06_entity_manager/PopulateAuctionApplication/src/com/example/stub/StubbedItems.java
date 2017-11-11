/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stub;

import com.example.model.Image;
import com.example.model.Item;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author tmcginn
 */
public class StubbedItems {

    private static ConcurrentHashMap<Integer, Item> items;
    private static int itemId = 100;
    private static final StubbedItems instance =
            new StubbedItems();

    private StubbedItems() {
        ConcurrentHashMap<Integer, Image> images = StubbedImages.getImages();
        // Item(int itemID, int imageID, String title, String description, String condition)
        items = new ConcurrentHashMap<>();
        int id = getNextId();
        
        // Item: 100
        Image image = images.get(2000);
        items.put(id, new Item(id, image, "Antique oak phone stand", "The beautiful antique phone stand could also serve as a small hall table. Mission style.", "USED"));
        
        // Item: 101
        id = getNextId();
        image = images.get(2001);
        items.put(id, new Item(id, image, "American Girl Doll - Beautiful - Please Look!", "This American Girl doll Just Like Me is in her original box with outfit and in excellent new condition.  She has long wavy blond hair, freckles, brown eyes and is gorgeous.  She was given as a gift, the box was opened only to discover that she is a duplicate gift.  She was never played with and needs a new owner.", "NEW"));

        // Item : 102
        id = getNextId();
        image = images.get(2002);
        items.put(id, new Item(id, image, "Antique coffee grinder made in pine", "Wake up and smell the coffee with this perfectly functional coffee grinder remeniscent of quieter, more relaxed times.", "USED"));
        
        // Item: 103
        id = getNextId();
        image = images.get(2003);
        items.put(id, new Item(id, image, "Pewter Salt and Pepper Shakers", "Vintage Salt and Pepper shakers in good condition. Some slight scratches", "USED"));

        // Item: 104
        id = getNextId();
        image = images.get(2004);
        items.put(id, new Item(id, image, "BRAND NEW LIL'KINZ POLAR BEAR!", "Sealed tag and never used code. From a non-smoking, pet-free home.", "NEW"));

        // Item: 105
        id = getNextId();
        image = images.get(2005);
        items.put(id, new Item(id, image, "North Face Surge Backback", "NEW - we bought this backback for my son and he hated the color!", "NEW"));

        // Item : 106
        id = getNextId();
        image = images.get(2006);
        items.put(id, new Item(id, image, "Artisan horse sculpture with hand-painted details", "A true conversation starter!", "USED"));

        // Item: 107
        id = getNextId();
        items.put(id, new Item(id, null, "HP Printer - not working - parts", "Broken HP printer - may have salvageble parts - sorry no picture available", "PARTS"));

    }

    public static ConcurrentHashMap<Integer, Item> getItems() {
        return items;
    }

    public static int getNextId() {

        return itemId++;
    }
}