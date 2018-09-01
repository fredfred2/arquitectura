package com.example.dao;

import com.example.model.Sales;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Model
public class SalesDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;
    

    public Sales createSalesRecord() throws SQLException {
        Sales newSalesRecord = new Sales();
        em.persist(newSalesRecord);
        em.flush();
        return newSalesRecord;
    }

    public void updateSalesRecord(Sales productSale) throws SQLException {
        em.merge(productSale);
    }

    public void removeSalesRecord(Sales productSales) throws SQLException {
        em.remove(productSales);
    }
}
