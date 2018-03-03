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
public class TemperatureConverter {

    String cTemp;

    /**
     * Creates a new instance of TemperatureConverter
     */
    public TemperatureConverter() {
    }

    public String getcTemp() {
        return cTemp;
    }

    public void setcTemp(String cTemp) {
        this.cTemp = cTemp;
    }

    public String getfTemp() {
        return ("");
    }

    public void setfTemp(String fTemp) {
        double f = -500;
        try {
            f = Double.parseDouble(fTemp);
        } catch (NumberFormatException nfe) {
            cTemp = "Invalid";

        }
        if (f >= -459.4) {
            double c = (f - 32) * (5.0 / 9.0);
            cTemp = String.format("%.2f", c);

        }
    }
}
