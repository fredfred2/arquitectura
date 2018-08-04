package com.example.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class WriteXmlTest {

  public static void WriteXML() {

    Person p = new Person("Tom");

    try {
      JAXBContext jc = JAXBContext.newInstance(Person.class);
      Marshaller m = jc.createMarshaller();
      m.marshal(p, new File("person.xml"));
      m.marshal(p, System.out);
    } catch (JAXBException e) {
      System.out.println("JAXB Exception: " + e.getMessage());
    }
  }
}
