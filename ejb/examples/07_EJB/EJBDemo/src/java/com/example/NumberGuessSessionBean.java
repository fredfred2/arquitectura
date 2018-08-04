package com.example;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class NumberGuessSessionBean {

    private final int MAX_GUESSES = 3;
    private int secret;
    private int guessCount;
    
    @PostConstruct
    private void init() {
        secret = (int)(Math.random() * 10 + 1);
        guessCount = 0;
    }
    
    public int getMaxGuesses() {
        return MAX_GUESSES;
    }
    
    public int getGuessCount() {
        return guessCount;
    }
    
    public void reset() {
        init();
    }
    
    public boolean guess(int guessValue) {
        System.out.println("Guess:" + guessValue + ",Secret:" + secret);
        if(guessCount < MAX_GUESSES) {
            guessCount++;
        } else {
            return false;
        }
        
        if(guessValue == secret) {
            return true;
        } else {
            return false;
        }
    }
    
}