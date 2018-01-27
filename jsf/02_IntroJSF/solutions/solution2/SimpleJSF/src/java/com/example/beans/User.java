/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author admin
 */
@Named
@RequestScoped
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String string) {
        this.name = string;
    }
}
