package com.example.dao;

import com.example.model.Sales;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.sql.DataSource;

@Model
public class SalesDAO implements Serializable {

    @Resource(lookup = "jdbc/JavaMartDB")
    private DataSource dataSource;

    public Sales createSalesRecord() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Sales (Total_Sale) VALUES (0)", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return new Sales(rs.getInt(1), new Date(), 0);
        }
    }

    public void updateSalesRecord(Sales productSale) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            // TODO: complete the method to update the sales record with productSale.getSales_id()
            // Note: use a prepared statement
        }
    }

    public void removeSalesRecord(Sales productSales) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM Sales WHERE Sales_Id = " + productSales.getSales_id());
        }
    }
}
