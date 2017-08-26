package com.example.listeners;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class NormalActionListener implements ActionListener{

	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		
	
	String value= (String)event.getComponent().getClientId().toString();
       //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("msg:", value+"  new");
	
        FacesContext.getCurrentInstance().addMessage(value,new FacesMessage( "clicked"));
        }
        }
	
