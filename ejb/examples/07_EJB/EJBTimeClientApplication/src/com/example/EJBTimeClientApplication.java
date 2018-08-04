package com.example;

import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBTimeClientApplication {

    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();  
            TimeSessionBeanRemote tsbr = (TimeSessionBeanRemote) ctx.lookup("java:global/EJBTimeServerApplication/TimeSessionBean");
            Date date = tsbr.getDate();
            System.out.println("Server Date:" + date);
        } catch (NamingException ne) {
            System.err.println("JNDI Problem");
            ne.printStackTrace();
            System.exit(1);
        }
    }
}
