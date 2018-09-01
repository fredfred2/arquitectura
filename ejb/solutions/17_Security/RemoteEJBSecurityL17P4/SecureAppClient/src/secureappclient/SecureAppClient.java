/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secureappclient;

import bean.StockStatusRemote;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class SecureAppClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    String jndiPath = "java:global/Secure/Secure-ejb/StockStatus";
           

            try {
                final Context cxt = getInitialContext();
                System.out.println("standaloneapp.main: looking up bean at: " + jndiPath);
                StockStatusRemote StockStatus = (StockStatusRemote) cxt.lookup(jndiPath);
                System.out.println(StockStatus.getStatus());
            }

        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static InitialContext getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        env.put(Context.SECURITY_PRINCIPAL, "admin");
        env.put(Context.SECURITY_CREDENTIALS, "welcome1");

        return new InitialContext(env);
    }

}
