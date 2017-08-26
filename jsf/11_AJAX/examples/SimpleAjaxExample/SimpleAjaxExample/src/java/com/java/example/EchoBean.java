package com.java.example;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author tmcginn
 */
@Named
@RequestScoped
public class EchoBean implements Serializable {

    private String text;

    public EchoBean() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCount() {
        return text == null ? 0: text.length();
    }
}

