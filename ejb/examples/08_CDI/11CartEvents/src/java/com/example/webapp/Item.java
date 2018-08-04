package com.example.webapp;

import java.io.Serializable;

public class Item  implements Serializable{
  private String description;
  private double amount;
  
  public Item(){}
   
  public void setItemData(String description, double amount){
    this.description = description;
    this.amount = amount;
  }
  
  @Override
  public String toString(){
    return "Description: " + this.description + " Amount: " + this.amount;
  }
}
