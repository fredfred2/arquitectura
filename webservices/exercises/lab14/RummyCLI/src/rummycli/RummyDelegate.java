package rummycli;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

public class RummyDelegate {

    private static final RummyDelegate instance = new RummyDelegate();
    private final String rummyRootUrl = "https://localhost:7002/IndianRummyWS";
    private String userName;

    public static RummyDelegate getInstance() {
        return instance;
    }

    public String getCurrentUserName() {
        return userName;
    }

    /**
     * Saves the user credentials for other method calls.
     * @param userName
     * @param password
     */
    public void setUserAndPass(final String userName, final String password) {
        this.userName = userName;
        Authenticator authinstance = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password.toCharArray());
            }
        };
        Authenticator.setDefault(authinstance);
    }

    /**
     * Tries to do a HEAD to /resources/players/{userName}
     * If HEAD returns 404 then try to create
     * Return true if a 201 response is returned
     * @param userName
     * @param password
     * @return Returns true if a new player was created successfully.
     */
    public boolean createPlayer(String userName, String password) {
        setUserAndPass(userName, password);
        // add jersey client code
        return true;
    }

    /**
     * Tries to do a GET to /resources/players/{userName}
     * If 200 then return true
     * @param userName
     * @param password
     * @return true if the player exists with the matching password
     */
    public boolean login(String userName, String password) {
        setUserAndPass(userName, password);
        // add jersey client code
        return true;
    }

    public boolean hasGames() {
        // add jersey client code
        return true;
    }

    public boolean doesPlayerExist(String input) {
        // add jersey client code
        return true;
    }

    public List<GameSummary> listMyGames() {
        // replace with jersey client code
        List<GameSummary> myGames = new ArrayList<>();
        GameSummary gs = new GameSummary();
        gs.addPlayerName("matt");
        gs.addPlayerName("tom");
        gs.addPlayerName("peter");
        gs.addPlayerName("mike");
        gs.setCurrentTurnPlayer("mike");
        myGames.add(gs);
        return myGames;
    }

    public GameDetails getGame(String gameId) {
        // replace with jersey client code
        GameDetails game = new GameDetails();
        game.addPlayerName("matt");
        game.addPlayerName("tom");
        game.addPlayerName("peter");
        game.addPlayerName("mike");
        game.setCurrentTurnPlayer("mike");

        List<String> hand = new ArrayList<>();
        hand.add("AS");
        hand.add("JC");
        hand.add("10H");
        hand.add("8D");
        hand.add("2C");
        hand.add("KH");
        hand.add("9C");
        hand.add("3D");
        hand.add("JH");
        hand.add("7S");
        hand.add("2H");
        hand.add("AD");
        hand.add("4S");

        game.setMyHand(hand);

        game.setDiscardTopCard("3H");

        game.setVersion("1");

        return game;
    }

    public boolean hasGameChanged(String gameId, String version) {
        // replace with jersey client code
        return false;
    }

    public void discard(String card) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void drawDiscardedCard(String gameId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void drawStockCard(String gameId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}