package com.example.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class WritePersonList {
  public static void main(String[] args) {
    List<Person> newList = new ArrayList<>();    
    
    newList.add(new Person("Tom"));
    newList.add(new Person("Edgar"));
    newList.add(new Person("Eduardo"));
    newList.add(new Person("Anjana"));
    
    PersonList pl = new PersonList(newList);
    
    try {
      JAXBContext jc = JAXBContext.newInstance(PersonList.class);
      Marshaller m = jc.createMarshaller();
      
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      
      m.marshal(pl, new File("personList.xml"));
      m.marshal(pl, System.out);
      
    } catch (JAXBException e) {
      System.out.println("JAXB Exception: " + e.getMessage());
    }
    
  }
}
