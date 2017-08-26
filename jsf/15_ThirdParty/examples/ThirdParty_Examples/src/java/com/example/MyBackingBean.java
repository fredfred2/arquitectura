package com.example;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("mybean")
@RequestScoped
public class MyBackingBean {

    private String prop;

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String submitForm() {
        System.out.println("Prop value:" + prop);
        return null;
    }
    
}