package com.example.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int itemId;
    private Image image;
    private String title;
    private String description;
    @Column(name = "ITEMCONDITION")
    private String condition;  // NEW, USED, PARTS

    public Item() {
    }

    public Item(int itemId, Image image, String title, String description, String condition) {
        this.itemId = itemId;
        this.image = image;
        this.title = title;
        this.description = description;
        this.condition = condition;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}