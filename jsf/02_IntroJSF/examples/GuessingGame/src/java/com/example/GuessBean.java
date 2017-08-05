/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author anshenoy
 */
@Named
@RequestScoped
public class GuessBean {
    private int answer;
    private int guess;
    /**
     * Creates a new instance of GuessBean
     */
    public GuessBean() {
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }
    
    public void checkAnswer() {
        String s = "";
        if (guess == answer) {
            s = "Congratulations! You guessed my number!";
        }else if (guess > answer) {
            s = "Too high!";
        } else if (guess < answer) {
            s = "Too Low";
        }
        FacesContext.getCurrentInstance().addMessage(null, 
                                      new FacesMessage(s));
    }
 
    @PostConstruct
    public void reset() {
        guess = 0;
        answer = new Random().nextInt(100);
        System.out.println ("Number is: " + answer);
    }
}

