package com.example.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

  private String name;
  private double price;
  private String description;
  
  public Product(){ }
  
  public Product(String name, double price, String description){
    this.name = name;
    this.price = price;
    this.description = description;
  }

  public String getName(){
    return name;
  }
  
  public double getPrice(){
    return price;
  }
  
  public String getDescription(){
    return description;
  }
}
