package com.example;

import java.util.logging.Logger;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

@RequestScoped
public class OrderReceiverBean {

    private static final Logger logger = Logger.getLogger("com.example.OrderReceiverBean");

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/orderQueue")
    private Queue queue;

    public OrderReceiverBean() {
    }

    public void receiveMessage() {
        try (JMSConsumer consumer = context.createConsumer(queue)) {
            Message m = consumer.receive(1000);
            if (m != null) {
                logger.log(Level.INFO, "Received message: " + m.getBody(String.class));
            } else {
                logger.log(Level.INFO, "Received no message - likely timed out.");
            }
        } catch (JMSException ex) {
            logger.log(Level.INFO,"exception" + ex);
        }

    }
}
