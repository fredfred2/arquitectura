package com.example.custom.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.java.example.Card")
public class CreditCardConverter implements Converter {

    // getAsObject strips any spaces or dashes from the credit card number
    // Card number entered must be a valid digit or an exception is thrown
    public Object getAsObject(FacesContext context, UIComponent component,
            String newValue) throws ConverterException {
        String convertedValue = null;
        if (newValue == null) {
            return newValue;
        }
        convertedValue = newValue.trim();
        char[] input = convertedValue.toCharArray();
        StringBuffer buffer = new StringBuffer(50);
        for (int i = 0; i < input.length; ++i) {
            if (input[i] == '-' || input[i] == ' ') {
                continue;
            } else if (Character.isDigit(input[i])) {
                buffer.append(input[i]);
            } else {
                FacesMessage message = new FacesMessage("Please enter only digits");
                throw new ConverterException(message);
            }
        }
        convertedValue = buffer.toString();
        return new CreditCard(convertedValue.toString());
    }

    // getAsString makes the card number readable by adding back spaces every
    // four characters
    public String getAsString(FacesContext context, UIComponent component,
            Object value) throws ConverterException {
        String inputVal = null;
        if (value == null) {
            return null;
        }
        try {
            inputVal = value.toString();
        } catch (ClassCastException ce) {
            FacesMessage err = new FacesMessage("Failed to convert object to String");
            throw new ConverterException(err);
        }
        char[] input = inputVal.toCharArray();
        StringBuffer buffer = new StringBuffer(50);
        for (int i = 0; i < input.length; ++i) {
            if ((i % 4) == 0 && i != 0) {
                if (input[i] != ' ' || input[i] != '-') {
                    buffer.append(" ");
                }
            }
            buffer.append(input[i]);
        }
        return buffer.toString();
    }
}
