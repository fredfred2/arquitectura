package com.example;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@RequestScoped
public class OrderProducerBean {

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/MiQueue")
    private Queue queue;

    public OrderProducerBean() {
    }
    
    public void sendMessage(String message) {
        context.createProducer().send(queue, message);
    }
}
