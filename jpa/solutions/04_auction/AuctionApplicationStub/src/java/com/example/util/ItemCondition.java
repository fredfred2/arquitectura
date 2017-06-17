/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

/**
 *
 * @author tmcginn
 */
public enum ItemCondition {
    NEW("New"), USED("Used"), PARTS("Parts");
    
    private final String label;
    
    private ItemCondition(String label) {
        this.label = label;
    }
    
    public String getLabel(){
        return this.label;
    }
}