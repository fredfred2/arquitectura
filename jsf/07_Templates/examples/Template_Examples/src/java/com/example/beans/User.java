package com.example.beans;

import java.io.Serializable; 
import javax.enterprise.context.SessionScoped;
import javax.inject.Named; 
   // or import javax.faces.bean.SessionScoped;
 
@Named("user") // or @ManagedBean(name="user")  
@SessionScoped     
public class User implements Serializable {
  private String name="JSF User";
  private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
  
  public String getName() { return name; }
  public void setName(String newValue) { name = newValue; }
    
 
}
