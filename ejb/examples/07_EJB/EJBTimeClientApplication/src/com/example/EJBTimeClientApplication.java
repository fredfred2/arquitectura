/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.TimeSessionBeanRemote;
import java.util.Date;
import java.util.Hashtable;
import javax.naming.*;

/**
 *
 * @author oracle
 */
public class EJBTimeClientApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String jndiPath = "java:global/EJBTimeServerApplication/EJBTimeServerApplication-ejb/TimeSessionBean";
        try {
            final Context cxt = getInitialContext();

            System.out.println("standaloneapp.main: looking up bean at: " + jndiPath);
            TimeSessionBeanRemote tsbr = (TimeSessionBeanRemote) cxt.lookup(jndiPath);

           Date date = tsbr.getDate();
            System.out.println("Server Date:" + date);
        } catch (Exception e) {
            System.err.println("Could not find TimeSessionBeanRemote");

            e.printStackTrace();
        }
    }

    private static InitialContext getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        return new InitialContext(env);
    }

}
