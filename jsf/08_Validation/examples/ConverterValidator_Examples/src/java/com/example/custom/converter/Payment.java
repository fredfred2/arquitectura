package com.example.custom.converter;

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author tmcginn
 */
@Named
@SessionScoped
public class Payment implements Serializable {
    private String name;
    private CreditCard cardNumber = new CreditCard ("");
    private String cardType;
    private ArrayList cardTypesAccepted;

    /** Creates a new instance of CreditCard */
    public Payment() {
        
    }

    public ArrayList getCardTypesAccepted () {
        cardTypesAccepted = new ArrayList ();
        cardTypesAccepted.add (new SelectItem ("AE","American Express"));
        cardTypesAccepted.add (new SelectItem ("DC","Diners Club"));
        cardTypesAccepted.add (new SelectItem ("MC","Mastercard"));
        cardTypesAccepted.add (new SelectItem ("VS", "VISA"));
        return cardTypesAccepted;
    }

    public CreditCard getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(CreditCard cardNumber) {
        this.cardNumber = cardNumber;
        System.out.println("converted value: "+cardNumber.toString());
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
