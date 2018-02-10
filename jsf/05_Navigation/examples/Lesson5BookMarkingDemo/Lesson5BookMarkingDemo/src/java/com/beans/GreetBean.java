/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author anshenoy
 */
@ManagedBean
@RequestScoped
public class GreetBean {

    /**
     * Creates a new instance of GreetBean
     */
    public GreetBean() {
    }
    
     private String greeting;
  private String msg = "Guys";
    /**
     * Creates a new instance of GreetBean
     */
 
    
     public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
    this.msg = greeting + ", " + msg;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
