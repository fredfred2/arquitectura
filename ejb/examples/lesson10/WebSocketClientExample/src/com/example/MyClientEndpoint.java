package com.example;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;

/**
 *
 * @author mheimer
 */
@ClientEndpoint
public class MyClientEndpoint {
    
    @OnMessage
    public void onMessage(String msg) {
        System.out.println("Received message: " + msg);
    }
    
}
