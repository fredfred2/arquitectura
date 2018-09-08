package rummycli;

import java.util.ArrayList;
import java.util.List;

public class GameSummary {

    private String gameId;
    private List<String> playerNames = new ArrayList<>();
    private String currentTurnPlayer;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public void addPlayerName(String playerName) {
        playerNames.add(playerName);
    }

    public String getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public void setCurrentTurnPlayer(String currentTurnPlayer) {
        this.currentTurnPlayer = currentTurnPlayer;
    }

    @Override
    public String toString() {
        StringBuilder playerList = new StringBuilder();
        playerList.append("Players: ");
        for (String playerName : playerNames) {
            playerList.append(playerName);
            if(playerName.equals(currentTurnPlayer)) {
                playerList.append("*");
            }
            playerList.append(", ");
        }
        return playerList.substring(0, playerList.length() - 2);
    }
}