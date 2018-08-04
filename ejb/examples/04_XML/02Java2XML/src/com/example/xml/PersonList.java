package com.example.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="personList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonList {
  
  @XmlElement(name="person")
  private List<Person> pList = new ArrayList<>();
  
  public PersonList(){}
  
  public PersonList(List<Person> pl){
    this.pList = pl;
  }
    
  public List<Person> getList(){
    return pList;
  } 
}
