/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author anshenoy
 */
@ManagedBean
@RequestScoped
public class AjaxBean {

    /**
     * Creates a new instance of AjaxBean
     */
   private String time;

    public String getTime() {
    return time;
  }

  public void showTime() {
    time = (new Date(System.currentTimeMillis())).toString();
  }
   
}
