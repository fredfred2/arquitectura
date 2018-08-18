package com.example.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Calc {

    @WebMethod// Annotate this method
    public String squared(String number) {
        double num = Double.valueOf(number);
        return Double.toString(num * num);

    }
}
