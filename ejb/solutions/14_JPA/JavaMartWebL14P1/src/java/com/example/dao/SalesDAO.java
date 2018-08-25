package com.example.dao;

import com.example.model.Sales;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Model
public class SalesDAO implements Serializable {

//    @Resource(lookup = "jdbc/JavaMartDB")
//    private DataSource dataSource;

        
    @PersistenceContext
private EntityManager em;
    public Sales createSalesRecord() throws SQLException {
        Sales newSalesRecord = new Sales();
em.persist(newSalesRecord);
em.flush();
return newSalesRecord;
    }

    public void updateSalesRecord(Sales productSale) throws SQLException {
//        try (Connection conn = dataSource.getConnection()) {
//            PreparedStatement pStmt = conn.prepareStatement("UPDATE Sales SET Date_Sold = ?, Total_Sale = ? WHERE Sales_id = ?");
//            pStmt.setInt(3, productSale.getSales_id());
//            pStmt.setDate(1, new java.sql.Date(productSale.getSales_date().getTime()));
//            pStmt.setDouble(2, productSale.getTotal_sale());
//            if (pStmt.executeUpdate() != 1) {
//                throw new SQLException("Failed to update the Sales record");
//            }
//        }
  em.merge(productSale);
    }

    public void removeSalesRecord(Sales productSales) throws SQLException {
//        try (Connection conn = dataSource.getConnection()) {
//            Statement stmt = conn.createStatement();
//            stmt.execute("DELETE FROM Sales WHERE Sales_Id = " + productSales.getSales_id());
//        }
  em.remove(productSales);
    }
}
