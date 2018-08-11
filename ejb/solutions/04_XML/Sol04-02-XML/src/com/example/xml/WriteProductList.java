package com.example.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class WriteProductList {
  public static void writeXML() {
    List<Product> newList = new ArrayList<>();    
    
    newList.add(new Product("Widget", 25.00, "The all new multifunction widget"));
    newList.add(new Product("Widget Pro", 35.00, "The all new multifunction widget pro"));
    newList.add(new Product("Widget Pro XL", 45.00, "The all new multifunction widget pro xl"));

    
    ProductList pl = new ProductList(newList);
    
    try {
      JAXBContext jc = JAXBContext.newInstance(ProductList.class);
      Marshaller m = jc.createMarshaller();
      
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      
      m.marshal(pl, new File("ProductList.xml"));
      m.marshal(pl, System.out);
      
    } catch (JAXBException e) {
      System.out.println("JAXB Exception: " + e.getMessage());
    }
    
  }
}
