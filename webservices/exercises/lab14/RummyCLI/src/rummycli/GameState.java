package rummycli;

import java.io.PrintStream;

public abstract class GameState {

    protected PrintStream out = System.out;
    protected final RummyDelegate rummyDelegate = RummyDelegate.getInstance();

    /**
     * Called by main game loop whenever there is a state change.
     */
    public void init() {
    }

    /**
     * Called every time in the main game loop before handleInput
     */
    public abstract void displayPrompt();

    /**
     * Processes a line of input from the user.
     *
     * @param input The trimmed text
     * @return A GameState reference. If "this" is returned then the main
     * application loop will skip calling init().
     */
    public abstract GameState handleInput(String input);

    /**
     * Called by child classes if they want to possibly allow quitting.
     * @param input
     */
    protected void quitOrNot(String input) {
        input = input.toLowerCase();
        if ("quit".startsWith(input)) {
            Thread.currentThread().interrupt();
        } else {
            out.println("Sorry, I didn't understand that.");
        }
    }

    /**
     * Called when the main loop kills the current state.
     */
    public void interrupt() {

    }

}
