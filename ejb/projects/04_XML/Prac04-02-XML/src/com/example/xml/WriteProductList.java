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
    
    // Create new products

    // Add products to ProductList
    
    try {
      JAXBContext jc = JAXBContext.newInstance(ProductList.class);
      Marshaller m = jc.createMarshaller();

      // Format the output
 
      // Marshall the objects
      
    } catch (JAXBException e) {
      System.out.println("JAXB Exception: " + e.getMessage());
    }
    
  }
}
