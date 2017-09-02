package rummycli.states;

import rummycli.GameState;

public class LoginState extends GameState {

    private String userName;

    @Override
    public void displayPrompt() {
        if (userName == null) {
            out.print("Enter a user name: ");
        } else {
            out.print("Enter a password: ");
        }
    }

    @Override
    public GameState handleInput(String input) {
        if (userName == null) {
            userName = input;
            return this;
        } else {
            String password = input;
            if (rummyDelegate.login(userName, password)) {
                return new PickGameState();
            } else {
                out.println("Sorry, that is not a valid combination");
                userName = null;
                return this;
            }
        }
    }
}