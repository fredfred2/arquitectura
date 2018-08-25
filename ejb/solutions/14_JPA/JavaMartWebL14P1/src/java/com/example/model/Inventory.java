package com.example.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity

public class Inventory implements Serializable {
    @Id
    private String product_id;
    @Column(name="AVAILABLE")
    private int quantity_on_hand;
    
    public Inventory() {
        
    }

    public Inventory(String product_id, int quantity_on_hand) {
        this.product_id = product_id;
        this.quantity_on_hand = quantity_on_hand;
    }

    public void add(int quantity) {
        quantity_on_hand += quantity;
    }
    
    public boolean subtract(int quantity) {
        if (quantity_on_hand - quantity >= 0) {
            quantity_on_hand -= quantity;
            return true;
        } else {
            return false;
        }
    }

    public int getQuantity_on_hand() {
        return quantity_on_hand;
    }
    
}
