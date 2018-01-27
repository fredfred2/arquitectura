/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author parodutt
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String check(){
        if(username.equalsIgnoreCase("jsf")){
        return "home";
        
    }
        else {return "invalid";}

    }
    }