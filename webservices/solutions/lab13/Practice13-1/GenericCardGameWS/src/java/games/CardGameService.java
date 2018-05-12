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
import weblogic.jws.Policies;
import weblogic.jws.Policy;

/**
 *
 * @author mheimer
 */
@SchemaValidation
@WebService(serviceName = "CardGameService")
@Policy(uri = "policy:Wssp1.2-2007-Wss1.1-UsernameToken-Plain-X509-Basic256.xml", attachToWsdl = true)
public class CardGameService {

    @EJB
    private GameStorageBean bean;
    private static final URL wsdlURL;
    private static final String wsdlLocation = "WEB-INF/wsdl/localhost_7001/CardDeckSessionBean/CardDeckSessionBeanService.wsdl";

    static {
        try {
            wsdlURL = new URL("file://./" + wsdlLocation);
        } catch (MalformedURLException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

@Policies({
    @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
    @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public String createCardGame(@WebParam(name = "deck-count") int numberOfDecks,
            @WebParam(name = "jokers-per-deck") int jokerCountPerDeck)
            throws GameException {

        if (numberOfDecks < 1 || numberOfDecks > 2) {
            throw new GameException("Only 1-2 decks supported");
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

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public boolean removeCardGame(@WebParam(name = "game-id") String gameId) {
        return bean.removeGame(gameId);
    }

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public void createPile(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "size") int pileSize,
            @WebParam(name = "pile") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.createPile(pileSize, pileName);
        }
    }

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
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

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public void createPiles(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "size") int pileSize,
            @WebParam(name = "dist-method") DistributionMethod dist,
            @WebParam(name = "pile") String... pileNames) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.createPiles(pileSize, dist, pileNames);
        }
    }

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public CardType showTopCard(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "pile") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            return game.showTopCard(pileName);
        }
    }

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public List<CardType> showPile(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "pile") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            return game.showPile(pileName);
        }
    }

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public void transferCards(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "source-pile") String sourcePile,
            @WebParam(name = "dest-pile") String destinationPile,
            @WebParam(name = "cards") int cardCount) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.transferCards(sourcePile, destinationPile, cardCount);
        }
    }

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public void shuffleAll(@WebParam(name = "game-id") String gameId) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.shuffleAll();
        }
    }

    @Policies({
        @Policy(uri = "policy:Wssp1.2-2007-SignBody.xml", attachToWsdl = true),
        @Policy(uri = "policy:Wssp1.2-2007-EncryptBody.xml", attachToWsdl = true)})
    public void shufflePile(@WebParam(name = "game-id") String gameId,
            @WebParam(name = "pile-name") String pileName) {
        CardGame game = bean.getGame(gameId);
        synchronized (game) {
            game.shufflePile(pileName);
        }
    }
}