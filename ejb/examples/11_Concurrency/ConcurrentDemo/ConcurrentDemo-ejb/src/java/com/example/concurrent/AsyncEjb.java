package com.example.concurrent;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class AsyncEjb {

    public void process() {
        doWork();
    }

    @Asynchronous
    public void processAsync() {
        doWork();
    }

    private void doWork() {
        int id = (int) (Math.random() * 1000);
        long start = System.currentTimeMillis();

        System.out.println("[" + (id) + "]AsyncProcess Started");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException ex) {
        }
        System.out.println("[" + (id) + "]AsyncProcess Finished Took:" + (System.currentTimeMillis() - start) + "ms.");
    }

}
