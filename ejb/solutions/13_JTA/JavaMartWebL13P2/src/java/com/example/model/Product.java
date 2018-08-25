package com.example.model;

import java.util.Objects;

public class Product {
    private String product_id;
    private String prod_name;
    private double price;
    private String prod_desc;

    public Product(String id, String name, double price, String description) {
        this.product_id = id;
        this.prod_name = name;
        this.price = price;
        this.prod_desc = description;
    }

    public String getId() {
        return product_id;
    }

    public String getName() {
        return prod_name;
    }

    public void setName(String name) {
        this.prod_name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return prod_desc;
    }

    public void setDescription(String description) {
        this.prod_desc = description;
    }
    
    @Override
    public String toString() {
        String s = String.format("Product id: %s\n"
        + "Name: %s\n"
        + "Description: %s\n"
        + "Price: $%f.2", product_id, prod_name, prod_desc, price);
        return s;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.product_id);
        hash = 97 * hash + Objects.hashCode(this.prod_name);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.prod_desc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.product_id, other.product_id)) {
            return false;
        }
        if (!Objects.equals(this.prod_name, other.prod_name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.prod_desc, other.prod_desc)) {
            return false;
        }
        return true;
    }
    
}
