/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;


 

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.ValueChangeEvent;
//import javax.inject.Named;
@ManagedBean(name="zipAutoFill") 
//@Named("zipAutoFill")
@RequestScoped 
public class AutoFillBean implements java.io.Serializable{ 
 private HtmlInputText inputName, inputZip, inputCity, inputState;
 
  public void zipAutoFillListener(ValueChangeEvent vce) throws IOException { 
    String zip = vce.getNewValue().toString(); 
 System.out.println(zip+"value change");
    StringBuilder city = new StringBuilder(); 
    StringBuilder state = new StringBuilder(); 
   // performLookup(zip, city, state);
      city.append("Dallas"); 
    state.append("Texas"); 
   
             inputCity.setValue(city.toString()); 
   inputState.setValue(state.toString()); 
  
  } 

    public HtmlInputText  getInputName() {
        return inputName;
    }

    public void setInputName(HtmlInputText inputName) {
        this.inputName = inputName;
    }

    public HtmlInputText getInputZip() {
        return inputZip;
    }

    public void setInputZip(HtmlInputText inputZip) {
        this.inputZip = inputZip;
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
 
  private void performLookup(String zip, StringBuilder city, 
        StringBuilder state) { 
    // In real life, you would actually perform the lookup. 
    // For now, we assume that every possible zip code is 
    // in Dallas, Texas. 
    city.append("Dallas"); 
    state.append("Texas"); 
 
  } 
 
 
}