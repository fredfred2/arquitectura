package com.example.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class WriteProductXML {

  public static void writeXML() {

    // Create a new product

    try {
      JAXBContext jc = JAXBContext.newInstance(Product.class);
      Marshaller m = jc.createMarshaller();
      // Marshall output

    } catch (JAXBException e) {
      System.out.println("JAXB Exception: " + e.getMessage());
    }
  }
}
