package rummy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import rummy.jaxb.Player;
import rummy.jaxb.PlayerStats;

@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class PlayersResource {

    private static final Logger logger = Logger.getLogger("rummy");

    @GET
    public List<Player> getPlayers(@Context UriInfo uriInfo) {
        if (!isGroupPresent("players")) {
            createGroup("players", "Shared group for all Indian Rummy players");
        }

        List<Player> list = new ArrayList<>();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        for (String userName : listGroupMembers("players")) {
            Player p = new Player();
            p.setUserName(userName);
            p.setHref(uriBuilder.clone().path(userName).build().toString());
            list.add(p);
        }
        return list;
    }

    @GET
    @Path("{id}")
    public Response getPlayer(@Context UriInfo uriInfo, @PathParam("id") String userName) {
        if (isUserPresent(userName) && isGroupPresent("players") && checkGroupMembership(userName, "players")) {
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            String desc = getUserDescription(userName);

            Player p = new Player();
            p.setUserName(userName);
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(PlayerStats.class);
                Unmarshaller u = jaxbContext.createUnmarshaller();
                PlayerStats stats = (PlayerStats) u.unmarshal(new ByteArrayInputStream(desc.getBytes()));
                p.setStats(stats);
            } catch (JAXBException ex) {
                logger.log(Level.SEVERE, "Could not read player stats", ex);
            }

            p.setHref(uriBuilder.build().toString());
            ResponseBuilder responseBuilder = Response.ok(p);
            return responseBuilder.build();
        } else {
            ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
            return responseBuilder.build();
        }
    }

    @PUT
    @Path("{id}")
    public Response createOrUpdatePlayer(@Context UriInfo uriInfo, @PathParam("id") String userName, Player player) {
        if (!isGroupPresent("players")) {
            createGroup("players", "Shared group for all Indian Rummy players");
        }

        if (isUserPresent(userName)) {
            if (userName.equals(player.getUserName())) {
                if (player.getPassword() != null) {
                    setPassword(player.getUserName(), player.getPassword());
                }
            } else {
                String statsString = getUserDescription(userName);
                deletePlayer(userName);
                createUser(player.getUserName(), player.getPassword(), statsString);
                joinGroup(userName, "players");
            }
            ResponseBuilder responseBuilder = Response.ok();
            return responseBuilder.build();
        } else {
            PlayerStats stats = new PlayerStats();
            String statsString = "";
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(PlayerStats.class);
                Marshaller m = jaxbContext.createMarshaller();
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                Writer writer = new OutputStreamWriter(bout);
                m.marshal(stats, writer);
                statsString = bout.toString();
            } catch (JAXBException ex) {
                logger.log(Level.SEVERE, "Could not read player stats", ex);
            }

            createUser(userName, player.getPassword(), statsString);
            joinGroup(userName, "players");
            ResponseBuilder responseBuilder = Response.created(uriInfo.getAbsolutePath());
            return responseBuilder.build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePlayer(@PathParam("id") String userName) {
        if (isUserPresent(userName) && isGroupPresent("players") && checkGroupMembership(userName, "players")) {
            deleteUser(userName);
            ResponseBuilder responseBuilder = Response.ok();
            return responseBuilder.build();
        } else {
            ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
            return responseBuilder.build();
        }
    }

    private static java.util.List<java.lang.String> listGroupMembers(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.listGroupMembers(groupName);
    }

    private static boolean isUserPresent(java.lang.String userName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.isUserPresent(userName);
    }

    private static void createUser(java.lang.String userName, java.lang.String password, java.lang.String description) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.createUser(userName, password, description);
    }

    private static String getUserDescription(java.lang.String userName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.getUserDescription(userName);
    }

    private static void setPassword(java.lang.String userName, java.lang.String password) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.setPassword(userName, password);
    }

    private static void deleteUser(java.lang.String userName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.deleteUser(userName);
    }

    private static boolean isGroupPresent(java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.isGroupPresent(groupName);
    }

    private static void createGroup(java.lang.String groupName, java.lang.String description) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.createGroup(groupName, description);
    }

    private static void joinGroup(java.lang.String userName, java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        port.joinGroup(userName, groupName);
    }

    private static boolean checkGroupMembership(java.lang.String userName, java.lang.String groupName) {
        users.UserManagementService service = new users.UserManagementService();
        users.UserManagementType port = service.getUserManagementPort();
        return port.checkGroupMembership(userName, groupName);
    }
}