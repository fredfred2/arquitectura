/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author parodutt
 */
@Named
@SessionScoped

public class Prefs implements java.io.Serializable{
    private boolean title;
private boolean releaseyear;
private boolean genre;

public Prefs() {
   this.title = true;
   this.releaseyear = true;
   this.genre = true;
}

    public boolean isTitle() {
        return title;
    }

    public void setTitle(boolean title) {
        this.title = title;
    }

    public boolean isReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(boolean year) {
        this.releaseyear = year;
    }

    public boolean isGenre() {
        return genre;
    }

    public void setGenre(boolean genre) {
        this.genre = genre;
    }

}
