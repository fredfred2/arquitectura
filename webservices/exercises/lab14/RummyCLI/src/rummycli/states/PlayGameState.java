package rummycli.states;

import rummycli.GameDetails;
import rummycli.GameState;

public class PlayGameState extends GameState {

    private String gameId;
    private GameDetails game = rummyDelegate.getGame(gameId);
    private GameChangeChecker thread;

    public PlayGameState(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public void init() {
        out.println(game);
        out.println("Polling for updates once per second");
        thread = new GameChangeChecker();
        thread.setDaemon(true);
    }

    @Override
    public void displayPrompt() {
        if(rummyDelegate.getCurrentUserName().equals(game.getCurrentTurnPlayer())) {
            if(game.getMyHand().size() == 13) {
                out.print("draw [S]tock, draw [D]iscard: ");
            } else {
                out.print("Pick a card to discard, 1-14: ");
            }
        } else {
            out.println("It is not your turn yet, hold on...");
        }
    }

    @Override
    public GameState handleInput(String input) {
        input = input.toLowerCase();
        if("quit".startsWith(input)) {
            thread.interrupt();
            return new PickGameState();
        } else if(rummyDelegate.getCurrentUserName().equals(game.getCurrentTurnPlayer())) {
            if(game.getMyHand().size() == 13) {
                if("stock".startsWith(input)) {
                    rummyDelegate.drawStockCard(gameId);
                    return this;
                } else if ("discard".startsWith(input)) {
                    rummyDelegate.drawDiscardedCard(gameId);
                    return this;
                } else {
                    out.println("Sorry, I didn't understand that.");
                    return this;
                }
            } else {
                try {
                    int cardId = Integer.parseInt(input);
                    if(cardId >= 1 && cardId <= 14) {
                        rummyDelegate.discard(game.getMyHand().get(cardId-1));
                        return this;
                    } else {
                        out.println("Error, Pick a number 1-14");
                        return this;
                    }
                } catch (NumberFormatException ex) {
                    out.println("Sorry, I didn't understand that.");
                    return this;
                }
            }
        } else {
            out.println("It is not your turn yet, hold on...");
            return this;
        }
    }

    @Override
    public void interrupt() {
        thread.interrupt();
    }

    private class GameChangeChecker extends Thread {
        @Override
        public void run() {
            while(!isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    boolean gameChanged = rummyDelegate.hasGameChanged(gameId, game.getVersion());
                    if(gameChanged) {
                        out.println("Game updated");
                        out.println(game);
                    }
                } catch (InterruptedException ex) {
                    return;
                }
            }
        }
    }
}