package com.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AsynchOrderConsumer {

    /**
     * Main method.
     *
     * @param args the destination name and type used by the example
     */
    public static void main(String[] args) {
        char answer = '\0';
        /*
         * In a try-with-resources block, create context.
         * Create consumer.
         * Register message listener (TextListener).
         * Receive text messages from destination.
         */
        try {
            
            Context jndiContext =  getInitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/myJMSConnectionFactory");
            JMSContext context = connectionFactory.createContext();
            Queue queue = (Queue) jndiContext.lookup("jms/orderQueue");
            JMSConsumer consumer = context.createConsumer(queue);
            MessageListener listener = new TextListener();
            consumer.setMessageListener(listener);

            System.out.println("To end program, enter Q or q, then <return>");
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);

            while (!((answer == 'q') || (answer == 'Q'))) {
                try {
                    answer = (char) inputStreamReader.read();
                } catch (IOException e) {
                    System.err.println("I/O exception: " + e.toString());
                }
            }
        } catch (NamingException | JMSRuntimeException e) {
            System.err.println("Exception occurred: " + e.toString());
            System.exit(1);
        }
        System.exit(0);
    }
    
    private static InitialContext getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        return new InitialContext(env);
    }
}
