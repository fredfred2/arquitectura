package games;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.ejb.Singleton;

/**
 *
 * @author mheimer
 */
@Singleton
public class GameStorageBean {

    private Map<String, CardGame> games = new HashMap<>();

    public CardGame getGame(String gameId) {
        return games.get(gameId);
    }

    public String addGame(CardGame game) {
        String id = UUID.randomUUID().toString();
        games.put(id, game);
        return id;
    }

    public boolean removeGame(String gameId) {
        CardGame game = games.remove(gameId);
        if (game == null) {
            return false;
        } else {
            return true;
        }
    }
}
