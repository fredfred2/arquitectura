package com.example.dao;

import com.example.model.Inventory;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Model
public class InventoryDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    // Get the Inventory for this Product
    public Inventory getInventory(String product_id) throws SQLException {
        Inventory inventory = em.find(Inventory.class, product_id);
        return inventory;
    }

    public void updateInventory(String product_id, int quantity) throws SQLException {
        Inventory inventory = new Inventory(product_id, quantity);
        em.merge(inventory);
    }
}
