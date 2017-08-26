/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author anshenoy
 */
@ManagedBean(name="user")
@RequestScoped
public class UserBean {

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        
        
        
    }
    
    private String name = "";
   private String password;

   public String getName() { return name; }
   public void setName(String newValue) { name = newValue; }

   public String getPassword() { return password; }
   public void setPassword(String newValue) { password = newValue; }
   
   public String getGreeting() { return name.length() == 0 ? "" : "Welcome to JSF2 + AJAX, " + name + "!"; 
}
   
}
