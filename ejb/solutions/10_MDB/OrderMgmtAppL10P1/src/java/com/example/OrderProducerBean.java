package com.example;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

@RequestScoped
public class OrderProducerBean {

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/orderTopic")
    private Topic topic;

    public OrderProducerBean() {
    }
    
    public void sendMessage(String message) {
        context.createProducer().send(topic, message);
    }
}
