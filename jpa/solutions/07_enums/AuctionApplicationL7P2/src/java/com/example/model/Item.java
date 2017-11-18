package com.example.model;

import com.example.util.ItemCondition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Item implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int itemId;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "IMAGEID")
    private Image image;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "ITEMCONDITION")
    private ItemCondition condition = ItemCondition.NEW;  // NEW, USED, PARTS
    // added L7P2
    @ElementCollection
    @CollectionTable(name="TAGS", joinColumns=@JoinColumn(name="ITEMID"))
    private List<String> keywords = new ArrayList<>();

    public Item() {
    }

    public Item(int itemId, Image image, String title, String description, ItemCondition condition) {
        this.itemId = itemId;
        this.image = image;
        this.title = title;
        this.description = description;
        this.condition = condition;
    }

    // added L7 P2
    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }
    
    public List<String> getKeyWords() {
        return keywords;
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
        // modified in 7
        return condition.name();
    }

    public void setCondition(String condition) {
        // modified in 7
        this.condition = ItemCondition.valueOf(condition);
    }
}