package com.example.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

@Stateless
public class ConcurrentUtilEjb {

    @Resource
    private ManagedExecutorService executorService;

    public String process() {
        return doWork();
    }

    public Future<String> processAsync() {
        return executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return doWork();
            }
        });
    }

    private String doWork() {
        int id = (int) (Math.random() * 1000);
        long start = System.currentTimeMillis();

        StringBuilder val = new StringBuilder();
        val.append("[").append(id).append("]Concurrency Started");
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException ex) {
        }
        val.append("[").append(id).append("]Concurrency Finished Took:").append(System.currentTimeMillis() - start).append("ms.");
        return val.toString();
    }
}
