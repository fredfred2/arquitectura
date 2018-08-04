package com.example;

import java.io.IOException;
import java.io.InputStreamReader;
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
        //TODO: In a try block, use JNDI to lookup the default ConnectionFactory, create the JMSContext and lookup the orderQueue

        //TODO: Create a JMSConsumer from the context, create an instance of TextListener and set the listener into the consumer
        System.out.println("To end program, enter Q or q, then <return>");
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);

        while (!((answer == 'q') || (answer == 'Q'))) {
            try {
                answer = (char) inputStreamReader.read();
            } catch (IOException e) {
                System.err.println("I/O exception: " + e.toString());
            }
        }
        //TODO: add the catch block for the try block

        System.exit(0);
    }
}
