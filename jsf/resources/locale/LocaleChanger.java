/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.beans;


import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author tmcginn
 */
@Named("locale")
@ApplicationScoped
public class LocaleChanger implements Serializable {

    private Locale currLocale = Locale.ENGLISH;
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getCurrLocale() {
        return currLocale;
    }

    public void langChanged () {
        FacesContext context = FacesContext.getCurrentInstance();
        currLocale = new Locale(language);
        context.getViewRoot().setLocale(currLocale);
    }
}

