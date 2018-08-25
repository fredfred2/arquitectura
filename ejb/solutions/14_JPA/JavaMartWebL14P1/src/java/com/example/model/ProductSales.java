package com.example.model;

import javax.persistence.*;

@Entity

public class ProductSales {
    @Id
    private int sales_id;
    private String product_id;
    @Column(name = "QUANTITY")
    private int quantity_sold;
    
    public ProductSales() {
        
    }

    public ProductSales(int sales_id, String product_id, int quantity_sold) {
        this.sales_id = sales_id;
        this.product_id = product_id;
        this.quantity_sold = quantity_sold;
    }

    public int getSales_id() {
        return sales_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }
            
}
