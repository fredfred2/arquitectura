package com.example.dao;

import com.example.model.ProductSales;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Model
public class ProductSalesDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void addProductSalesRecord(ProductSales salesRecord) throws SQLException {
        em.persist(salesRecord);
    }

    public List<ProductSales> getProductSalesBySalesID(int sales_ID) throws SQLException {
        String queryString = "SELECT p from ProductSales p WHERE p.sales_id =" + sales_ID;
        TypedQuery<ProductSales> query = em.createQuery(queryString, ProductSales.class);
        List<ProductSales> pSales = query.getResultList();
        return pSales;
    }
}
