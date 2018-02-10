/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author anshenoy
 */
@Named(value = "hello")
@RequestScoped
public class Hello {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a new instance of Hello
     */
    public Hello() {
    }
}
