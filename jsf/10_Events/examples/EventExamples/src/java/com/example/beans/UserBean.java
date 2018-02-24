package com.example.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("user")
//@ManagedBean(name="user")
@SessionScoped
//@RequestScoped
public class UserBean implements java.io.Serializable{

	private String username;
	private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
	public String outcome(){
           
		return "result";
	}

	public String getUsername() {
		return username;
	}
        public String display(){
           System.out.println("outcome invoked")	; 
		return "result1";
	}


	public void setUsername(String username) {
	System.out.println(" set property invoked")	;
            this.username = username;
	}
	
}