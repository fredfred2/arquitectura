/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author anshenoy
 */
@Named(value = "simpleController")
@SessionScoped
public class simpleController implements Serializable {
private String message="";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

public String doNavigation() {
if (message.trim().length() < 2) {
return("too-short");
} 
else {

return("correct");
}
}
    /**
     * Creates a new instance of simpleController
     */
    public simpleController() {
    }
}
