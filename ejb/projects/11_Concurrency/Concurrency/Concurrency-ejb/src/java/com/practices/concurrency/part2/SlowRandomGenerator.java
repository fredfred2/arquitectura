package com.practices.concurrency.part2;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class SlowRandomGenerator {

    public int getRandomNumber() {
        return generateRandomNumber();
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
