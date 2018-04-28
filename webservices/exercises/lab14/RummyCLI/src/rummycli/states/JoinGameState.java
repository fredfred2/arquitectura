package rummycli.states;

import java.util.List;
import rummycli.GameState;
import rummycli.GameSummary;

public class JoinGameState extends GameState {

    private List<GameSummary> myGames = rummyDelegate.listMyGames();

    @Override
    public void displayPrompt() {
        out.println("GAMES");
        for(int i = 1; i <= myGames.size(); i++) {
            out.println(i + ") " + myGames.get(i-1).toString());
        }
        out.print("Pick a game: ");
    }

    @Override
    public GameState handleInput(String input) {
        input = input.toLowerCase();
        int gameIndex;
        try {
            gameIndex = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            quitOrNot(input);
            if(myGames.size() <=1) {
                out.println("Enter \"1\" or \"quit\"");
            } else {
                out.println("Pick a number 1-" + myGames.size());
            }
            return this;
        }
        if(gameIndex < 1 || gameIndex > myGames.size()) {
            if(myGames.size() <=1) {
                out.println("Enter \"1\" or \"quit\"");
            } else {
                out.println("Error, Pick a number 1-" + myGames.size());
            }
            return this;
        } else {
            return new PlayGameState(myGames.get(gameIndex-1).getGameId());
        }
    }
}