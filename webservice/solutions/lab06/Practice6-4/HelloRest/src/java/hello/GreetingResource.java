/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author mheimer
 */
@Path("greeting")
public class GreetingResource {

    private static String text = "Hello";
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GreetingResource
     */
    public GreetingResource() {
    }

    /**
     * Retrieves representation of an instance of hello.GreetingResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        return text;
    }

    /**
     * PUT method for updating or creating an instance of GreetingResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
        text = content;
    }
}
