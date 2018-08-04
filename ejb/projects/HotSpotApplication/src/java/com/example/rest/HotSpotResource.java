package com.example.rest;

import com.example.model.VoteSingleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author mheimer
 */
@Path("/votes")
public class HotSpotResource {

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public VoteTotal getVotes(@PathParam("id") String id) {
        VoteSingleton vs = VoteSingleton.getInstance();
        VoteTotal vt = new VoteTotal();
        vt.id = id;
        vt.votes = vs.getVotes(id);
        return vt;
    }

    @POST
    @Path("/like/{id}")
    public void like(@PathParam("id") String id) {
        VoteSingleton vs = VoteSingleton.getInstance();
        vs.like(id);
    }

    @POST
    @Path("/dislike/{id}")
    public void dislike(@PathParam("id") String id, @Context SecurityContext sc) {
        VoteSingleton vs = VoteSingleton.getInstance();
        vs.dislike(id);
    }

}
