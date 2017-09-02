package rummycli.states;

import rummycli.GameState;

public class WelcomeState extends GameState {

    @Override
    public void init() {
        out.println("Welcome to Indian Rummy!");
        out.println("Press enter 3 times anytime to return to this menu.");
    }

    @Override
    public void displayPrompt() {
        out.print("[J]oin or [L]ogin or [Q]uit: ");
    }

    @Override
    public GameState handleInput(String input) {
        input = input.toLowerCase();
        if ("join".startsWith(input)) {
            return new CreateAccount();
        } else if ("login".startsWith(input)) {
            return new LoginState();
        } else {
            quitOrNot(input);
            return this;
        }
    }
}