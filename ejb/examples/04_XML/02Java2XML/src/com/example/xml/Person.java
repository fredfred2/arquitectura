package com.example.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

  private String name;

  private Person(){}
  
  public Person(String name){
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  

  @Override
  public String toString(){
    return "Name: " + name;
  }
}
