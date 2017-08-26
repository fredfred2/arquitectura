package com.java.example;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

/**
 *
 * @author tmcginn
 */
@FacesValidator("com.java.example.EMailValidator")
public class EMailValidator implements Validator, Serializable {

    public void validate (FacesContext context, UIComponent toValidate, Object value) {
        String message = "";
        String email = (String) value;
        if (!email.contains("@")) {
            ((UIInput) toValidate).setValid(false);
            message = "Please be sure to enter a valid email address: name@provider";
            context.addMessage(toValidate.getClientId(context),
                    new FacesMessage(message));
        }
    }
}
