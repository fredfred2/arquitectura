package com.example.dao;

import com.example.model.Product;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Model
public class ProductDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public List<Product> getAllProducts() throws SQLException {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> prodList = query.getResultList();
        return prodList;
    }

    public Product getProductById(String product_id) throws SQLException {
        Product prod = em.find(Product.class, product_id);
        return prod;
    }
    
    public void addNewProduct(Product newProd) {
        em.persist(newProd);
        em.flush();
    }

}
