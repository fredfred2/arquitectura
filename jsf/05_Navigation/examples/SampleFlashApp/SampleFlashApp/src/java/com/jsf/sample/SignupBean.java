/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jsf.sample;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author tmcginn
 */
@Named("signup")
@RequestScoped
public class SignupBean implements Serializable {
    private String name;
    private String street;
    private String city;
    private String zip;
    private String email;

    /** Creates a new instance of SignupBean */
    public SignupBean() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String add () {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        setName ((String) flash.get ("name"));
        setStreet ((String) flash.get ("street"));
        setZip ((String) flash.get ("zip"));
        setEmail ((String) flash.get ("email"));
        return ("done");
    }

}
