package com.example.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity

public class Inventory implements Serializable {

    @Id
    @NotNull(message = "The primary key may not be null")
    private String product_id;
    @Column(name = "AVAILABLE")
    @Min(value = 0, message = "The Inventory quantity must be greater than or equal to 0")
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
