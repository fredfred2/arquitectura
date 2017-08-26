package com.example.beans;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.inject.Named; 
   // or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped; 
   // or import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;

@Named("user1") // or @ManagedBean(name="user")
@SessionScoped
public class UserBean1 implements Serializable {
   private String name = "";
   private String password;
   private boolean loggedIn;

   public String getName() { return name; }
   public void setName(String newValue) { name = newValue; }

   public String getPassword() { return password; }
   public void setPassword(String newValue) { password = newValue; }
   
   public boolean isLoggedIn() { return loggedIn; }
   
   public String login()  {
      loggedIn = true;
      return "index_7";
   }
   
   public String logout() {
      loggedIn = false;
      return "login";
   }
   
   public void checkLogin(ComponentSystemEvent event) {      
      if (!loggedIn) {
         FacesContext context = FacesContext.getCurrentInstance();
         ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler)
            context.getApplication().getNavigationHandler();
         handler.performNavigation("login");
      }
   }   
}
