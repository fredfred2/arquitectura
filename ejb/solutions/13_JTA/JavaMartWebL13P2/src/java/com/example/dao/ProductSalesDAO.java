package com.example.dao;

import com.example.model.ProductSales;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.sql.DataSource;

@Model
public class ProductSalesDAO implements Serializable {

    @Resource(lookup = "jdbc/JavaMartDB")
    private DataSource dataSource;

    public void addProductSalesRecord(ProductSales salesRecord) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pStmt = conn.prepareCall("INSERT INTO ProductSales VALUES(?, ?, ?)");
            pStmt.setInt(1, salesRecord.getSales_id());
            pStmt.setString(2, salesRecord.getProduct_id());
            pStmt.setInt(3, salesRecord.getQuantity_sold());
            pStmt.executeUpdate();
        }
    }

    public List<ProductSales> getProductSalesBySalesID(int sales_ID) throws SQLException {
        List<ProductSales> pSales = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ProductSales WHERE Sales_ID = " + sales_ID);
            while (rs.next()) {
                int sales_id = rs.getInt("Sales_ID");
                String product_id = rs.getString("Product_ID");
                int quantity_sold = rs.getInt("Quantity");
                pSales.add(new ProductSales(sales_id, product_id, quantity_sold));
            }
        }
        return pSales;
    }
}
