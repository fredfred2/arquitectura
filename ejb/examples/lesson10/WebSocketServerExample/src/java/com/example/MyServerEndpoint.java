package com.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
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
    public void onOpen(Session session, @PathParam("stock") String stock) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Async remote = session.getAsyncRemote();
                remote.sendText(stock + ":" + ThreadLocalRandom.current().nextInt(1, 10));
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
