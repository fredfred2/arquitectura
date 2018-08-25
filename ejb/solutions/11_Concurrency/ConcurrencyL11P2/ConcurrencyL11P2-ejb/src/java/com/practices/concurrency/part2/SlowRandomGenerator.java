package com.practices.concurrency.part2;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class SlowRandomGenerator {

    @Asynchronous
    public Future<Integer> getRandomNumber() {
        final int number = generateRandomNumber();
        return new AsyncResult<>(number);
    }

    private int generateRandomNumber() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SlowRandomGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int) (Math.random() * 100);
    }
}
