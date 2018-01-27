/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import java.io.IOException;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ValueChangeEvent;
/**
 *
 * @author admin
 */
@javax.faces.bean.ManagedBean(name="autofill")
@RequestScoped 
public class ManagedBean implements java.io.Serializable{ 
    


 private HtmlInputText inputID,inputFname, inputLname, inputCity, inputState;

    public HtmlInputText getInputID() {
        return inputID;
    }

    public void setInputID(HtmlInputText inputID) {
        this.inputID = inputID;
    }

   
  public void autofillListener(ValueChangeEvent vce) throws IOException { 
    String id = vce.getNewValue().toString(); 
 StringBuilder firstname = new StringBuilder(); 
    StringBuilder lastname = new StringBuilder(); 
    StringBuilder city = new StringBuilder(); 
    StringBuilder state = new StringBuilder(); 
   retrieveInfo(id, firstname,lastname,city, state);
   
    inputFname.setValue(firstname.toString()); 
   inputLname.setValue(lastname.toString()); 
   inputCity.setValue(city.toString()); 
   inputState.setValue(state.toString()); 
   
  } 

    

    public HtmlInputText getInputFname() {
        return inputFname;
    }

    public void setInputFname(HtmlInputText inputFname) {
        this.inputFname = inputFname;
    }

    public HtmlInputText getInputLname() {
        return inputLname;
    }

    public void setInputLname(HtmlInputText inputLname) {
        this.inputLname = inputLname;
    }

    public HtmlInputText getInputCity() {
        return inputCity;
    }

    public void setInputCity(HtmlInputText inputCity) {
        this.inputCity = inputCity;
    }

    public HtmlInputText getInputState() {
        return inputState;
    }

    public void setInputState(HtmlInputText inputState) {
        this.inputState = inputState;
    }

    
 
  private void retrieveInfo(String id,StringBuilder firstname, StringBuilder lastname,StringBuilder city,  StringBuilder state) { 
    //code to fetch data of student with the specific id
      firstname.append("Henry");
   lastname.append("Scott");
      city.append("Dallas"); 
    state.append("Texas"); 
    
 
  } 
}
