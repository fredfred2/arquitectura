/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jsf.sample;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author tmcginn
 */
@Named
@RequestScoped
public class UserBean implements Serializable {
    private Integer item;
    private String name;
    /** Creates a new instance of UserBean */
    public UserBean() {
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
