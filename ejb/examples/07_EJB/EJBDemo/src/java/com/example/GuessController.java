package com.example;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "guessController")
@SessionScoped
public class GuessController implements Serializable {

    @EJB private NumberGuessSessionBean numGuessSB;
    
    private int myGuess;

    public int getMyGuess() {
        return myGuess;
    }

    public void setMyGuess(int myGuess) {
        this.myGuess = myGuess;
    }
    
    public int guessesLeft() {
        return numGuessSB.getMaxGuesses() - numGuessSB.getGuessCount();
    }
    
    public String restart() {
        myGuess = 0;
        numGuessSB.reset();
        return "index";
    }
    
    public String guess() {
        if(guessesLeft() <= 0) {
            return "lose";
        }
        boolean result = numGuessSB.guess(myGuess);
        if(result) {
            return "win";
        } if(guessesLeft() == 0) {
            return "lose";
        } else {
            return "index";
        }
    }
    
}
