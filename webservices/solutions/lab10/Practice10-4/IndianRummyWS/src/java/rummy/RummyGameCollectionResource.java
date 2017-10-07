package rummy;

import cards.CardType;
import cards.DistributionMethod;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import rummy.jaxb.Player;
import rummy.jaxb.RummyGame;
import util.CardUtils;

public class RummyGameCollectionResource {

    private static final Logger logger = Logger.getLogger("rummy");

    @POST
    public Response createGame(@Context UriInfo uriInfo, RummyGame rummyGame) {
        ResponseBuilder rb;

        // make sure all players are valid and unique
        Set<String> s = new HashSet<>();
        for (Player player : rummyGame.getPlayers()) {
            String userName = player.getUserName();
            if (!s.add(userName)
                    || !isGroupPresent("players")
                    || !isUserPresent(userName)
                    || !checkGroupMembership(userName, "players")) {
                logger.log(Level.WARNING, "Not player: {0}", userName);
                rb = Response.status(Response.Status.BAD_REQUEST);
                return rb.build();
            }
        }

        // create a game with the correct number of decks
        String gameId;
        int players = rummyGame.getPlayers().size();
        if (players >= 2 && players <= 3) {
            gameId = createCardGame(1, 2);
        } else if (players >= 4 && players <= 6) {
            gameId = createCardGame(2, 2);
        } else if (players >= 7 && players <= 10) {
            gameId = createCardGame(3, 2);
        } else {
            rb = Response.status(Response.Status.BAD_REQUEST);
            return rb.build();
        }

        // create a empty new game object
        // create game group
        RummyGame newGame = new RummyGame();
        createGroup("game-" + gameId, "");
        for (Player player : rummyGame.getPlayers()) {
            String userName = player.getUserName();
            joinGroup(userName, "game-" + gameId);
            newGame.addPlayer(player);
        }

        // figure out which player goes first
        String firstPlayer = null;
        while (firstPlayer == null) {
            giveEachPlayerOneCard(gameId);
            firstPlayer = getPlayerWithHighestCard(gameId);
        }
        newGame.setCurrentTurnPlayer(firstPlayer);

        dealGame(gameId);

        // save game description/state to game group
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RummyGame.class, CardType.class);
            Marshaller m = jaxbContext.createMarshaller();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            Writer writer = new OutputStreamWriter(bout);
            m.marshal(newGame, writer);
            String gameStr = new String(bout.toByteArray());
            setGroupDescription("game-" + gameId, gameStr);
        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, "Failed to generated game XML", ex);
            deleteGroup("game-" + gameId);
            rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            return rb.build();
        }

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(gameId);
        rb = Response.created(uriBuilder.build(newGame));
        return rb.build();
    }

@GET
public List<RummyGame> getGames(@Context UriInfo uriInfo) {
    List<RummyGame> list = new ArrayList<>();
    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
    for (String groupName : listGroupsByPattern("game-*")) {
        RummyGame rg = new RummyGame();
        String gameId = groupName.substring(5);
        rg.setHref(uriBuilder.clone().path(gameId).build().toString());
        list.add(rg);
    }
    return list;
}

@DELETE
public void deleteGames() {
    for (String groupName : listGroupsByPattern("game-*")) {
        String gameId = groupName.substring(5);
        removeCardGame(gameId);
        deleteGroup(groupName);
    }
}

    @Path("{id}")
    public RummyGameResource getGameResource() {
        return new RummyGameResource();
    }

    private void dealGame(String gameId) {
        shuffleAll(gameId);
        List<String> userNames = listGroupMembers("game-" + gameId);
        // piles are named "player-USERNAME"
        // because someone will have a name like "discard"
        for (int i = 0; i < userNames.size(); i++) {
            userNames.set(i, "player-" + userNames.get(i));
        }
        createPiles(gameId, 13, DistributionMethod.ROUND_ROBIN, userNames);
        createPile(gameId, 1, "discard");
        createAllSupplyPile(gameId, "stock");
    }

    private void giveEachPlayerOneCard(String gameId) {
        shuffleAll(gameId);
        List<String> userNames = listGroupMembers("game-" + gameId);
        for (String userName : userNames) {
            String playerPile = "player-" + userName;
            createPile(gameId, 1, playerPile);
        }
    }

    // finds the player with the highest top card
    private String getPlayerWithHighestCard(String gameId) {
        List<String> userNames = listGroupMembers("game-" + gameId);
        String topPlayer = "";
        String highRank = "2";
        boolean tie = false;
        for (String userName : userNames) {
            String playerPile = "player-" + userName;
            CardType playerCard = showTopCard(gameId, playerPile);
            int result = CardUtils.compareCards(highRank, playerCard.getRank());
            if (result == 0) {
                tie = true;
                topPlayer = userName;
            } else if (result == -1) {
                highRank = playerCard.getRank();
                tie = false;
                topPlayer = userName;
            }
        }
        if (tie) {
            return null;
        } else {
            return topPlayer;
        }
    }

    private static void createAllSupplyPile(java.lang.String gameId, java.lang.String pile) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        port.createAllSupplyPile(gameId, pile);
    }

    private static String createCardGame(int deckCount, int jokersPerDeck) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        return port.createCardGame(deckCount, jokersPerDeck);
    }

    private static void createPile(java.lang.String gameId, int size, java.lang.String pile) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        port.createPile(gameId, size, pile);
    }

    private static void createPiles(java.lang.String gameId, int size, cards.DistributionMethod distMethod, java.util.List<java.lang.String> pile) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        port.createPiles(gameId, size, distMethod, pile);
    }

    private static boolean removeCardGame(java.lang.String gameId) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        return port.removeCardGame(gameId);
    }

    private void shuffleAll(java.lang.String gameId) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        port.shuffleAll(gameId);
    }

    private CardType showTopCard(java.lang.String gameId, java.lang.String pile) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        return port.showTopCard(gameId, pile);
    }

    private void createGroup(java.lang.String groupName, java.lang.String description) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.createGroup(groupName, description);
    }

    private boolean isUserPresent(java.lang.String userName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.isUserPresent(userName);
    }

    private void joinGroup(java.lang.String userName, java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.joinGroup(userName, groupName);
    }

    private java.util.List<java.lang.String> listGroupMembers(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.listGroupMembers(groupName);
    }

    private void setGroupDescription(java.lang.String groupName, java.lang.String description) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.setGroupDescription(groupName, description);
    }

    private void deleteGroup(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.deleteGroup(groupName);
    }

    private static boolean checkGroupMembership(java.lang.String userName, java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.checkGroupMembership(userName, groupName);
    }

    private static boolean isGroupPresent(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.isGroupPresent(groupName);
    }

    private static java.util.List<java.lang.String> listGroupsByPattern(java.lang.String groupPattern) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.listGroupsByPattern(groupPattern);
    }
}