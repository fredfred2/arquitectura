package com.example.jaxws;

import javax.activation.DataSource;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Calc {
@WebMethod
    public String squared(String number) throws EmptyStringException{
        if("".equals(number)){
            throw new EmptyStringException("Esta mal tu cadena");
        }
        double num = Double.valueOf(number);
        return Double.toString(num * num);
    }
    
    public void notifica() throws InterruptedException{Thread.sleep(5000);}
}
