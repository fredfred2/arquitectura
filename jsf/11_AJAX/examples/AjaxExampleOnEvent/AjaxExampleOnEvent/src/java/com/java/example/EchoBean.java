package com.java.example;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;


@Named
@RequestScoped
public class EchoBean implements Serializable {

    private String text;
     private int eventCount = 0;

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
    public int getEventCount() {
           return eventCount;
     }
     public void update(AjaxBehaviorEvent event) {
          
        eventCount++;
  
     }
}

