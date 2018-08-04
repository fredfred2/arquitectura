package com.example;

import java.io.StringWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mheimer
 */
@ServerEndpoint("/ticker/{stock}")
public class MyServerEndpoint {

    private static ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture future;

    @OnOpen
    public void onOpen(Session session, @PathParam("stock") String sym) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int price = ThreadLocalRandom.current().nextInt(1, 10);
                Async remote = session.getAsyncRemote();
                StringWriter sw = new StringWriter();
                JsonWriter jw = Json.createWriter(sw);
                JsonObject stock = Json.createObjectBuilder()
                        .add("symbol", sym)
                        .add("price", price)
                        .build();
                jw.writeObject(stock);
                jw.close();
                remote.sendText(sw.toString());
            }
        };
        future = ses.scheduleAtFixedRate(r, 0, 3, TimeUnit.SECONDS);
    }

    @OnMessage
    public void onMessage(String message) {

    }

    @OnClose
    public void onClose() {
        future.cancel(false);
    }
}
