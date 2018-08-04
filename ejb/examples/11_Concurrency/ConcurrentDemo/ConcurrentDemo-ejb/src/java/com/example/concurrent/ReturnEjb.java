package com.example.concurrent;

import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Stateless;

@Stateless
public class ReturnEjb {

    public String process() {
        return doWork();
    }

    public Future<String> processAsync() {
        return new AsyncResult<>(doWork());
    }

    private String doWork() {
        int id = (int) (Math.random() * 1000);
        long start = System.currentTimeMillis();

        StringBuilder val = new StringBuilder();
        val.append("[").append(id).append("]AsyncReturn Started");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException ex) {
        }
        val.append("[").append(id).append("]AsyncReturn Finished Took:").append(System.currentTimeMillis() - start).append("ms.");
        return val.toString();
    }
}
