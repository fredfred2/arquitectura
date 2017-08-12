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
public class Catalog implements Serializable {
    private Integer currentItem;
    /** Creates a new instance of Catalog */
    public Catalog() {
    }

    public Integer getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Integer currentItem) {
        this.currentItem = currentItem;
    }

}
