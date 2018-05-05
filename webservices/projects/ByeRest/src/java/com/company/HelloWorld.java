/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.net.URI;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author fredfred2
 */
@Path("greetings")
public class HelloWorld {
   
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloWorld
     */
    public HelloWorld() {
    }

    @GET
    @Produces("text/plain")
    public String getText() {
        return "Hello";
    }
    
    @GET
    @Path("general")
    @Produces("text/plain")
    public String getGreetingGeneral() {
        return "Hello in general";
    }
    
    /**
     *
     * @return
     */
    @GET
    @Path("fancy")
    @Produces(MediaType.APPLICATION_JSON)
    public SaludoFancy getFancyGreeting() {
        SaludoFancy fancy=new SaludoFancy();
        fancy.setDate(new Date());
        fancy.setName("Super fancy");
        return fancy;
    }
    
    @POST
    @Path("fancy")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SaludoFancy getFancyGreeting(SaludoFancy fancy) {
        fancy.setDate(new Date());
        fancy.setName("Super fancy");
        return fancy;
    }
    @POST
    @Path("fancyVoid")
    @Consumes(MediaType.APPLICATION_JSON)
    public void getFancyGreetingVoid(SaludoFancy fancy) {
        fancy.setDate(new Date());
        fancy.setName("Super fancy");
    }
    
    
    @POST
    @Path("fancyException")
    @Consumes(MediaType.APPLICATION_JSON)
    public void getFancyGreetingException(SaludoFancy fancy) {
        throw new NullPointerException("error");
    }
    
    @POST
    @Path("fancyResponse")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFancyResponse(SaludoFancy fancy) {
        URI uri=URI.create("1000");
        Response.ResponseBuilder bulder=Response.created(uri);
        fancy.setDate(new Date());
        fancy.setName("Super fancy");
        final Response res = bulder.status(404).entity(fancy).build();
        
        return res;
    }

}
