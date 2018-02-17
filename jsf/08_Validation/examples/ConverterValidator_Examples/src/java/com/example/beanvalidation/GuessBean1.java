package com.example.beanvalidation;

import java.io.Serializable;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Named
@SessionScoped
public class GuessBean1 implements Serializable {
    private static final int max = 100;

    @NotNull(message="Please enter at least one digit.")
    @Min(1)
    @Max(max)
    private int guess;
    private int answer;
    
    public GuessBean1() {
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
