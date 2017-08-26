package hfive;

import java.io.IOException;

import javax.faces.component.NamingContainer;
import javax.faces.context.FacesContext;

public class Canvas implements NamingContainer {
  public void encodeBegin(FacesContext fc) throws IOException {
     
  }
  public void encodeEnd(FacesContext fc) throws IOException {
     System.out.println("Encoding");
  }
}