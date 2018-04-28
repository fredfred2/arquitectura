package rummycli.states;

import java.util.HashSet;
import java.util.Set;
import rummycli.GameState;

public class NewGameState extends GameState {

    private int playerCount = 0;
    private Set<String> names = new HashSet<>();

    @Override
    public void init() {
        names.add(rummyDelegate.getCurrentUserName());
    }

    @Override
    public void displayPrompt() {
        if (playerCount >= 2 && playerCount <= 10) {
            out.print("Player Name: ");
        } else {
            out.print("How many players: ");
        }
    }

    @Override
    public GameState handleInput(String input) {
        if(input.equalsIgnoreCase(rummyDelegate.getCurrentUserName())) {
            out.println("You are always in the games you create.");
        } else if (playerCount == 0) {
            try {
                int i = Integer.parseInt(input);
                if (i >= 2 && i <= 10) {
                    playerCount = i;
                    out.println("Who would you like to play against?");
                } else {
                    out.println("Please select a value between 2 and 10.");
                }
            } catch (NumberFormatException ex) {
                out.println("Please select a value between 2 and 10.");
            }
        } else {
            boolean exists = rummyDelegate.doesPlayerExist(input);
            if(exists && !names.contains(input)) {
                names.add(input);
                if(names.size() < playerCount) {
                StringBuilder playerList = new StringBuilder();
                playerList.append("Players so far: ");
                for(String playerName : names) {
                    playerList.append(playerName);
                    playerList.append(", ");
                }
                out.println(playerList.substring(0, playerList.length() -1));
                } else {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            } else if(exists && names.contains(input)) {
                out.println("You already added that person!");
            } else {
                out.println("Sorry, that is not a valid player.");
            }
        }
        return this;
    }
}