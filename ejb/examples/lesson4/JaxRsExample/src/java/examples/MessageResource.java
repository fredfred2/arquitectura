package examples;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("message")
public class MessageResource {
    
    private static String message = "Howdy";

    @GET
    public String getMessage() {
        return message;
    }
    
    @PUT
    public void setMessage(String msg) {
        message = msg;
    }

}
