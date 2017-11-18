package com.example.ejb;

import com.example.model.Item;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ItemFacade {

    // added
    @PersistenceContext(unitName = "AuctionPU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(ItemFacade.class.getName());

    public ItemFacade() {
    }

    public Item findByItemId(int itemId) {
        Item item = null;
        try {
            item = em.find(Item.class, itemId);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return item;
    }

	// Added L7P2
    public void addItemTags(int itemId, String tags) {
        Item item;
        try {
            item = em.find(Item.class, itemId);
            String[] tagTokens = tags.trim().toLowerCase().split("\\s*,\\s*");
            if (tagTokens.length > 0) {
                for (String tag : tagTokens) {
                    item.addKeyword(tag);
                }
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }
}