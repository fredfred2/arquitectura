package com.java.example;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author tmcginn
 */
@Named("mail")
@SessionScoped
public class SendMail implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String sendMail () {
        // Process the mail transaction
        return "success";
    }
}
