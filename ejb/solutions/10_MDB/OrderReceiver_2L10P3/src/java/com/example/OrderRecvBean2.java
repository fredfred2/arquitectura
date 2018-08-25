package com.example;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
    //@ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/orderTopic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/orderTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/orderTopic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class OrderRecvBean2 implements MessageListener {

    private static final Logger logger = Logger.getLogger("com.example.OrderRecvBean2");

    public OrderRecvBean2() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            logger.log(Level.INFO, "OrderRecvBean2: " + message.getBody(String.class));
        } catch (JMSException ex) {
            logger.log(Level.INFO, ex.getMessage());
        }
    }

}
