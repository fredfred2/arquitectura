package com.example.model;

public class Item {

    private int itemId;
    private Image image;
    private String title;
    private String description;
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

    @Override
    public String toString() {
        return "Item{" + "itemId=" + itemId + ", image=" + image + ", title=" + title + ", description=" + description + ", condition=" + condition + '}';
    }
}