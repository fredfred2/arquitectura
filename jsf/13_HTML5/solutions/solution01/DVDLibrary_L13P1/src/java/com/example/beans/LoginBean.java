/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

/**
 *
 * @author admin
 */
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named("login")
@SessionScoped

public class LoginBean implements Serializable{
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
public String doLogin()
{
    return "index";
}
}