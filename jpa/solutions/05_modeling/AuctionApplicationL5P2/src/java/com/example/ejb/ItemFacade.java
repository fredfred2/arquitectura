package com.example.ejb;

import com.example.model.Item;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class ItemFacade {

    private static final Logger LOG = Logger.getLogger(ItemFacade.class.getName());

    public ItemFacade() {
    }

    public Item findByItemId(int itemId) {
        Item item = null;
        // Practice 6-1 code goes here

        return item;
    }

    public void addItemTags(int itemId, String tags) {
    }
}