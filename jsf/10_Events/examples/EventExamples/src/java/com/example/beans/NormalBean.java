package com.example.beans;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
 
@Named("normal")
@SessionScoped
public class NormalBean implements java.io.Serializable{

	private String buttonId; 
	
	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public void printIt(ActionEvent event){
		
		//Get submit button id
		buttonId = event.getComponent().getClientId();
               setButtonId( "my value");
		
	}
	
	public String outcome(){
		return "result_1";
	}
}