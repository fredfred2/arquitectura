/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import javax.enterprise.context.RequestScoped;
      //  SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;


/**
 *
 * @author admin
 */
@Named ("customer")
@RequestScoped
//@SessionScoped

public class Customer implements java.io.Serializable {

    public Customer() {
        number="001";
        name="Zen";
        type="Platinum";
    }
   
 private String number;
 private String name;
 private String type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void selectType(ActionEvent evt){
        
      
          this.number = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("num");
          this.name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("custname");
          this.type = (String)evt.getComponent().getAttributes().get("cust-type");
                    
}

}
  

