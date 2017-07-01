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

    private static void addMessage(FacesMessage.Severity severity, String msg) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSummary(msg);
        message.setSeverity(severity);
        ctx.addMessage(null, message);
    }

    public static void addInfoMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, msg);
    }

    public static void addErrorMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, msg);
    }

    public static void addWarnMessage(String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, msg);
    }
}
