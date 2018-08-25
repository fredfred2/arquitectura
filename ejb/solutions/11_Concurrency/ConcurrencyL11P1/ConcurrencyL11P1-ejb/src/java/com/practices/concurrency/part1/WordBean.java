package com.practices.concurrency.part1;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class WordBean {

    @Asynchronous
    public void register(String word) {
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep((long) (Math.random() * 3000));
        } catch (InterruptedException ex) {
            Logger.getLogger(WordBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Logger.getLogger(WordBean.class.getName()).log(Level.INFO, "Word: {0} registered. StartTime: {1} in {2}ms.", new Object[]{word, startTime, System.currentTimeMillis() - startTime});
    }
}
