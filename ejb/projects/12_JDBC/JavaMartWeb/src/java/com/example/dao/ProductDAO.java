package com.example.dao;

import com.example.model.Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.sql.DataSource;

@Model
public class ProductDAO implements Serializable {

    @Resource(lookup = "jdbc/JavaMartDB")
    private DataSource dataSource;

    private final String idCol = "PRODUCT_ID";
    private final String nameCol = "PROD_NAME";
    private final String priceCol = "PRICE";
    private final String descCol = "PROD_DESC";

    public List<Product> getAllProducts() throws SQLException {
        List<Product> prodList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            // TODO: Use the appropriate SQL statement to get all of the products from the database
        }
        return prodList;
    }

    public Product getProductById(String product_id) throws SQLException {
        Product prod = null;
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            // TODO: Use a select statement to return a specific Product entity based on the product_id
        }
        return prod;
    }

}
