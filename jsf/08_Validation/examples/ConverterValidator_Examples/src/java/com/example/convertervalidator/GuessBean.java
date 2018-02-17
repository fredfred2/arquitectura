package com.example.convertervalidator;

import java.io.Serializable;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class GuessBean implements Serializable {

    private static final int max = 100;
    private int answer;
    private int guess;

    public GuessBean() {
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getMax() {
        return max;
    }

    public void checkAnswer() {
        String s = "";
        if (guess == answer) {
            s = "Congratulations! You guessed my number!";
        } else if (guess > answer) {
            s = "Too high!";
        } else if (guess < answer) {
            s = "Too Low";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s));
    }

    @PostConstruct
    public void reset() {
        guess = 0;
        answer = new Random().nextInt(max);
        System.out.println("Number is: " + answer);
    }
}
