package rummycli.states;

import rummycli.GameState;

public class PickGameState extends GameState {

    @Override
    public void displayPrompt() {
        if(rummyDelegate.hasGames()) {
            out.print("[J]oin current game or [N]ew game: ");
        } else {
            out.print("No current games, [N]ew game: ");
        }
    }

    @Override
    public GameState handleInput(String input) {
        input = input.toLowerCase();
        if("new".startsWith(input)) {
            return new NewGameState();
        } else if(rummyDelegate.hasGames() && "join".startsWith(input)) {
            return new JoinGameState();
        } else {
            quitOrNot(input);
            return this;
        }
    }

}