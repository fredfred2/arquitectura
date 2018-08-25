package com.example.dao;

import com.example.model.Inventory;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Model
public class InventoryDAO implements Serializable {

//    @Resource(lookup = "jdbc/JavaMartDB")
//    private DataSource dataSource;
    
    
    @PersistenceContext
private EntityManager em;
    // Get the Inventory for this Product
    public Inventory getInventory(String product_id) throws SQLException {
//        Inventory inventory = null;
//        try (Connection conn = dataSource.getConnection()) {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM INVENTORY WHERE Product_ID = '" + product_id + "'");
//            rs.next();
//            inventory = new Inventory(product_id, rs.getInt("AVAILABLE"));
//        }
        Inventory inventory = em.find(Inventory.class, product_id);
        return inventory;
    }

    public void updateInventory(String product_id, int quantity) throws SQLException {
//        try (Connection conn = dataSource.getConnection()) {
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate("UPDATE INVENTORY SET Available = " + quantity + " WHERE Product_ID = '" + product_id + "'");
   
//    }
  Inventory inventory = new Inventory(product_id, quantity);
  em.merge(inventory);
    }
}
