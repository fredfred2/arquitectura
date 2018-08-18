/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.example.CalculatorBeanRemote;
import java.util.Hashtable;
import javax.naming.*;

/**
 *
 * @author oracle
 */
public class EnterpriseCalculatorClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String jndiPath = "java:global/EnterpriseCalculator/EnterpriseCalculator-ejb/CalculatorBean";
        try {
            final Context cxt = getInitialContext();

            System.out.println("standaloneapp.main: looking up bean at: " + jndiPath);
            CalculatorBeanRemote CalculatorBean = (CalculatorBeanRemote) cxt.lookup(jndiPath);

            System.out.println("5 + 5 = " + CalculatorBean.add(5, 5));
            System.out.println("5 - 5 = " + CalculatorBean.subtract(5, 5));
        } catch (Exception e) {
            System.err.println("Could not find CalculatorBeanRemote");

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
