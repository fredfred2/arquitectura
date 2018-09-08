package rummycli;

import java.util.Scanner;
import rummycli.states.WelcomeState;

public class RummyCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameState oldState = null;
        GameState currentState = new WelcomeState();
        int emptyCount = 0;
        while(!Thread.currentThread().isInterrupted()) {
            if(currentState != oldState) {
                oldState = currentState;
                currentState.init();
            }
            currentState.displayPrompt();
            String line = scanner.nextLine();
            line = line.trim();
            if(line.length() > 0) {
                emptyCount = 0;
                currentState = currentState.handleInput(line);
            } else if(emptyCount >= 2) {
                emptyCount = 0;
                currentState.interrupt();
                currentState = new WelcomeState();
            } else {
                emptyCount++;
            }
        }

    }

}