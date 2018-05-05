package rummy;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import rummy.jaxb.Link;

@Path("/")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class IndianRummyRootResource {

    @GET
    public List<Link> getLinks(@Context UriInfo uriInfo) {
        List<Link> links = new ArrayList<>();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

        Link playerLink = new Link();
        UriBuilder playerUri = uriBuilder.clone().path("/players");
        playerLink.href = playerUri.build().toString();
        playerLink.rel = "players";
        links.add(playerLink);

        return links;
    }

    @Path("/players")
    public PlayersResource getPlayerResource() {
        return new PlayersResource();
    }

}