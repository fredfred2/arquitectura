package com.example.jaxws;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Status;
import javax.transaction.TransactionSynchronizationRegistry;

@WebService
@Stateless
public class Calc1EJB implements ICalc {

    @Resource
    TransactionSynchronizationRegistry tsr;

    @WebMethod
    public String squared(String number) throws EmptyStringException {
        if ("".equals(number)) {
            throw new EmptyStringException("Esta mal tu cadena");
        }
        double num = Double.valueOf(number);
        return Double.toString(num * num);

    }

    public void notifica() {
        System.out.println(tsr.getTransactionStatus());
    }
}
