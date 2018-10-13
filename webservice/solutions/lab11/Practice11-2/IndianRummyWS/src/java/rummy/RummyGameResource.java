package rummy;

import cards.CardType;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import rummy.jaxb.Hand;
import rummy.jaxb.Player;
import rummy.jaxb.RummyGame;

public class RummyGameResource {

    private static final Logger logger = Logger.getLogger("rummy");

    @GET
    public RummyGame getGame(@Context UriInfo uriInfo, @PathParam("id") String gameId) throws JAXBException {
        if (!isGroupPresent("game-" + gameId)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(RummyGame.class);
        Unmarshaller u = jaxbContext.createUnmarshaller();
        String groupDesc = getGroupDescription("game-" + gameId);
        ByteArrayInputStream in = new ByteArrayInputStream(groupDesc.getBytes());
        RummyGame game = (RummyGame) u.unmarshal(in);

        CardType topDiscardCard = showTopCard(gameId, "discard");
        game.setTopDiscardCard(topDiscardCard);

        for (Player player : game.getPlayers()) {
            Hand hand = new Hand();
            player.setHand(hand);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            URI uri = uriBuilder.path("/hands/" + player.getUserName()).build();
            hand.setHref(uri.toString());
        }

        game.setHref(uriInfo.getAbsolutePath().toString());
        return game;
    }

    @DELETE
    public Response deleteGame(@PathParam("id") String gameId) {
        ResponseBuilder rb;

        if (!isGroupPresent("game-" + gameId)) {
            rb = Response.status(Response.Status.NOT_FOUND);
            return rb.build();
        } else {
            removeCardGame(gameId);
            deleteGroup("game-" + gameId);
            return Response.ok().build();
        }
    }

    @GET
    @Path("/hands/{user}")
    public Response getHand(@Context UriInfo uriInfo, @PathParam("id") String gameId, @PathParam("user") String userName) {
        ResponseBuilder rb;

        if (!isGroupPresent("game-" + gameId)) {
            rb = Response.status(Response.Status.NOT_FOUND);
            return rb.build();
        }

        List<CardType> cards = showPile(gameId, "player-" + userName);
        if (cards == null || cards.isEmpty()) {
            rb = Response.status(Response.Status.NOT_FOUND);
            return rb.build();
        }

        Hand hand = new Hand();
        hand.setCards(cards);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path("/hands/" + userName).build();
        hand.setHref(uri.toString());

        return Response.ok(hand).build();
    }

    private static String getGroupDescription(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.getGroupDescription(groupName);
    }

    private static void deleteGroup(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.deleteGroup(groupName);
    }

    private static boolean isGroupPresent(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.isGroupPresent(groupName);
    }

    private static CardType showTopCard(java.lang.String gameId, java.lang.String pile) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        return port.showTopCard(gameId, pile);
    }

    private static java.util.List<cards.CardType> showPile(java.lang.String gameId, java.lang.String pile) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        return port.showPile(gameId, pile);
    }

    private static boolean removeCardGame(java.lang.String gameId) {
        cards.CardGameService_Service service = new cards.CardGameService_Service();
        cards.CardGameService port = service.getCardGameServicePort();
        return port.removeCardGame(gameId);
    }
}