package games;

import com.sun.xml.ws.developer.SchemaValidation;
import deckws.CardDeckSessionBean;
import deckws.CardDeckSessionBeanService;
import deckws.CardType;
import deckws.StackType;
import games.CardGame.DistributionMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author mheimer
 */
@SchemaValidation
@WebService(serviceName = "CardGameService")
public class CardGameService {

    @EJB
    private GameStorageBean bean;
    private static final URL wsdlURL;
    private static final String wsdlLocation = "WEB-INF/wsdl/localhost_7001/CardDeckSessionBean/CardDeckSessionBeanService.wsdl";

    static {
        try {
            wsdlURL = new URL(
                    "http://localhost:7001/CardDeckSessionBean/CardDeckSessionBeanService?wsdl");
        } catch (MalformedURLException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public String createCardGame(@WebParam(name = "deck-count") int numberOfDecks,
            @WebParam(name = "jokers-per-deck") int jokerCountPerDeck)
            throws GameException {

        if (numberOfDecks < 1 || numberOfDecks > 2) {
            throw new GameException("Only 1-2 decks supported");
        }

        if (numberOfDecks < 1 || numberOfDecks > 10) {

}



        if (jokerCountPerDeck < 0 || jokerCountPerDeck > 4) {
            throw new IllegalArgumentException("Only 0-4 jokers per deck supported");
        }

        CardDeckSessionBeanService service = new CardDeckSessionBeanService(wsdlURL);
        CardDeckSessionBean port = service.getCardDeckSessionBeanPort();

        CardGame game = new CardGame();

        for (int i = 0; i < numberOfDecks; i++) {
            int deckId = port.createDeck(jokerCountPerDeck);
            StackType deck = port.getDeck(deckId);
            game.addCards(deck.getCard());
            port.deleteDeck(deckId);
        }

        return bean.addGame(game);
    }

    public boolean removeCardGame(@WebParam(name = "game-id") String gameId) {
        return bean.removeGame(gameId);
    }

    public void createPile(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "size") int pileSize,
            @WebParam(name = "pile") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.createPile(pileSize, pileName);
        }
    }

    @WebMethod(operationName = "create-all-supply-pile")
    @ResponseWrapper(className = "CreateAllSupplyPileResponse")
    @RequestWrapper(className = "CreateAllSupplyPile")
    public void createPile(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "pile") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.createPile(game.getSupplySize(), pileName);
        }
    }

    public void createPiles(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "size") int pileSize,
            @WebParam(name = "dist-method") DistributionMethod dist,
            @WebParam(name = "pile") String... pileNames) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.createPiles(pileSize, dist, pileNames);
        }
    }

    public CardType showTopCard(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "pile") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            return game.showTopCard(pileName);
        }
    }

    public List<CardType> showPile(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "pile") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            return game.showPile(pileName);
        }
    }

    public void transferCards(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "source-pile") String sourcePile,
            @WebParam(name = "dest-pile") String destinationPile,
            @WebParam(name = "cards") int cardCount) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.transferCards(sourcePile, destinationPile, cardCount);
        }
    }

    public void shuffleAll(@WebParam(name = "game-id") String gameId) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.shuffleAll();
        }
    }

    public void shufflePile(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "pile-name") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.shufflePile(pileName);
        }
    }
}