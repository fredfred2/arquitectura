package examples;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("message")
public class MessageResource {
    
    @GET
    @RolesAllowed(value = {"user"})
    public String getUserMessage(@Context SecurityContext sc) {
        String principal = "anonymous";
        boolean inRole = false;
        if(sc.getUserPrincipal() != null) {
            principal = sc.getUserPrincipal().getName();
            inRole = sc.isUserInRole("user");
        }
        return "User:" + principal + ", isUserInRole:" + inRole;
    }
    
    @GET
    @RolesAllowed(value = {"admin"})
    @Path("secret")
    public String getAdminMessage(@Context SecurityContext sc) {
        String principal = "anonymous";
        boolean inRole = false;
        if(sc.getUserPrincipal() != null) {
            principal = sc.getUserPrincipal().getName();
            inRole = sc.isUserInRole("admin");
        }
        return "Admin:" + principal + ", isUserInRole:" + inRole;
    }

}
