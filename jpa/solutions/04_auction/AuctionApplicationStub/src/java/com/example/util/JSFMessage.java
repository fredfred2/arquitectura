/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author tmcginn
 */
public final class JSFMessage {

    public static void addInfoMessage(String msg) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(msg);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        ctx.addMessage(null, message);
    }

    public static void addErrorMessage(String msg) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(msg);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        ctx.addMessage(null, message);
    }
}
