package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.DeploymentException;
import javax.websocket.Session;

/**
 *
 * @author mheimer
 */
public class MyApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URI uri = new URI("ws://localhost:8080/WebSocketServerExample/ticker/duke");
            javax.websocket.WebSocketContainer container
                    = javax.websocket.ContainerProvider.getWebSocketContainer();
            try (Session session = container.connectToServer(MyClientEndpoint.class, uri)) {
                System.out.println("Press Enter to close");
                System.in.read();
            }
        } catch (URISyntaxException | IOException | DeploymentException ex) {
            ex.printStackTrace();
        }
    }
}
