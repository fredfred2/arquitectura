package com.example.dao;

import com.example.model.Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import javax.sql.DataSource;

@Model
public class ProductDAO implements Serializable {

//    @Resource(lookup = "jdbc/JavaMartDB")
//    private DataSource dataSource;
//
//    private final String idCol = "PRODUCT_ID";
//    private final String nameCol = "PROD_NAME";
//    private final String priceCol = "PRICE";
//    private final String descCol = "PROD_DESC";
    
    @PersistenceContext
private EntityManager em;

    public List<Product> getAllProducts() throws SQLException {
//        List<Product> prodList = new ArrayList<>();
//        try (Connection conn = dataSource.getConnection()) {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
//            while (rs.next()) {
//                String id = rs.getString(idCol);
//                String name = rs.getString(nameCol);
//                double price = rs.getDouble(priceCol);
//                String desc = rs.getString(descCol);
//                prodList.add(new Product(id, name, price, desc));
//            }
//        }
TypedQuery<Product> query 
  = em.createQuery("SELECT p FROM Product p", Product.class);
List<Product> prodList = query.getResultList(); 
        return prodList;
    }

    public Product getProductById(String product_id) throws SQLException {
//        Product prod = null;
//        try (Connection conn = dataSource.getConnection()) {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT WHERE Product_ID = '" + product_id + "'");
//            rs.next();
//            String id = rs.getString(idCol);
//            String name = rs.getString(nameCol);
//            double price = rs.getDouble(priceCol);
//            String desc = rs.getString(descCol);
//            prod = new Product(id, name, price, desc);
//        }
        Product prod = em.find(Product.class, product_id);
        return prod;
    }

}
