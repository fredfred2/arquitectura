package examples;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 *
 * @author mheimer
 */
public class JaxRsClient {

    public static void main(String[] args) {
        String baseURL
                = "http://localhost:8080/JaxRsExample/resources";
        Client client = ClientBuilder.newClient();
        WebTarget target
                = client.target(baseURL);

        String stringResult = target
                .path("message")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);

//        target = target.path("message");
//        Builder builder = target.request(MediaType.TEXT_PLAIN);
//        String result = builder.get(String.class);
        System.out.println("Result: " + stringResult);

        Response msgResponse = target
                .path("message")
                .request()
                .put(Entity.text("Hello"));
        int status = msgResponse.getStatus();
        Map<String, NewCookie> cookies = msgResponse.getCookies();
        String power = msgResponse.getHeaderString("X-Powered-By");
        Date date = msgResponse.getDate();
        if (status >= 400 && status < 500) {
            System.out.println("Client error");
        } else if (status >= 500) {
            System.out.println("Server error");
        } else {
            System.out.println("STATUS: " + status);
        }

        List<User> listResult = target
                .path("p/people2")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<User>>() {
                });

        System.out.println("Result: " + listResult);

        Response response = target
                .path("p/people")
                .request(MediaType.APPLICATION_JSON)
                .get();

        List<User> l = response.readEntity(new GenericType<List<User>>() {
        });
        System.out.println("Result: " + l);

    }
}
