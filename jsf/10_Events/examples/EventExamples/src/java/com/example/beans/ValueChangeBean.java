/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;


import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author admin
 */

public class ValueChangeBean implements ValueChangeListener {
   
  @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
if (null != event.getNewValue())
{ 
   
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msg", "Hello"+ event.getNewValue());
   
   
        //FacesContext.getCurrentInstance().getViewRoot().
}
}
}