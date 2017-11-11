package com.example.ejb;

import com.example.model.Image;
import com.example.model.Item;
import com.example.stub.StubbedItems;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ItemFacade {

    private static final Logger LOG = Logger.getLogger(ItemFacade.class.getName());
    @Inject
    private ImageFacade imageFacade;

    public ItemFacade() {
    }

    public Item findByItemId(int itemId) {
        ConcurrentHashMap<Integer, Item> items = StubbedItems.getItems();
        return items.get(itemId);

    }

    public Item addItem(Item item) {
        int itemId = StubbedItems.getNextId();
        item.setItemId(itemId);
        ConcurrentHashMap<Integer, Item> items = StubbedItems.getItems();
        items.put(itemId, item);
        return item;
    }

    public void removeItem(Item item) {
        Image image = item.getImage();
        if (image != null) {
            imageFacade.removeImage(image);
        }
        ConcurrentHashMap<Integer, Item> items = StubbedItems.getItems();
        items.remove(item.getItemId());
    }

    public Item updateItem(Item item, int imageId) {
        Image image = null;
        if (imageId != 0) {
            image = imageFacade.findImageById(imageId);
        }
        item.setImage(image);
        return item;
    }
}