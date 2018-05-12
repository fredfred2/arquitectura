package rummy.oauth;

import com.sun.jersey.api.representation.Form;
import com.sun.jersey.oauth.server.api.providers.DefaultOAuthProvider;
import com.sun.jersey.oauth.server.api.providers.DefaultOAuthProvider.Consumer;
import com.sun.jersey.oauth.server.spi.OAuthProvider;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@RolesAllowed({"player", "admin"})
@Path("/oauth")
@Produces({MediaType.TEXT_HTML})
public class OAuthRootResource {

    private @Context
    UriInfo uriInfo;
    private @Context
    SecurityContext secCtx;
    private @Context
    OAuthProvider oauth;
    private UriBuilder uriBuilder;

    @PostConstruct
    private void init() {
        uriBuilder = uriInfo.getBaseUriBuilder().path("oauth");
    }

    @GET
    public String getPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>OAuth</title></head>");
        sb.append("<body>");
        sb.append("<a href='");
        sb.append(uriBuilder.clone().path("register").build());
        sb.append("'>Register</a>");
        sb.append("<br/>");
        sb.append("<a href='");
        sb.append(uriBuilder.clone().path("list").build());
        sb.append("'>List</a>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @Path("register")
    @GET
    public String getRegisterPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>OAuth</title></head>");
        sb.append("<body>");
        sb.append("<form action='");
        sb.append(uriBuilder.clone().path("register").build());
        sb.append("' method='POST'>");
        sb.append("<label for='name-input'>Application Name:</label><input id='name-input' type='text' name='name'/>");
        sb.append("<input type='submit' value='Register'/>");
        sb.append("</form>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @Path("register")
    @POST
    public String postForm(Form form) {
        String developer = secCtx.getUserPrincipal().getName();
        Consumer client = ((DefaultOAuthProvider) oauth).registerConsumer(developer, form);

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>OAuth</title></head>");
        sb.append("<body>");
        sb.append("<h1>Client Credentials</h1>");
        sb.append("Name: ");
        sb.append(client.getAttributes().getFirst("name"));
        sb.append("<br/>");
        sb.append("Key: ");
        sb.append(client.getKey());
        sb.append("<br/>");
        sb.append("Secret: ");
        sb.append(client.getSecret());
        sb.append("<br/>");
        sb.append("<a href='");
        sb.append(uriBuilder.clone().build());
        sb.append("'>Back</a>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @Path("list")
    @GET
    public String getListPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>OAuth</title></head>");
        sb.append("<body>");
        String developer = secCtx.getUserPrincipal().getName();
        Set<Consumer> clientCreds = ((DefaultOAuthProvider) oauth).getConsumers(developer);
        if (clientCreds.size() > 0) {
            sb.append("<h1>Client Credentials</h1>");
            for (Consumer client : clientCreds) {
                sb.append("Name: ");
                sb.append(client.getAttributes().getFirst("name"));
                sb.append("<br/>");
                sb.append("Key: ");
                sb.append(client.getKey());
                sb.append("<br/>");
                sb.append("Secret: ");
                sb.append(client.getSecret());
                sb.append("<br/><br/>");
            }
        } else {
            sb.append("You have no client credentials under the name ");
            sb.append(developer);
        }
        sb.append("<br/>");
        sb.append("<a href='");
        sb.append(uriBuilder.clone().build());
        sb.append("'>Back</a>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @Path("authorize")
    @GET
    public String getAuthorizePage(@QueryParam("oauth_token") String token) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>OAuth</title></head>");
        sb.append("<body>");
        String owner = secCtx.getUserPrincipal().getName();
        if (token == null) {
            sb.append("Missing oauth_token parameter");
        } else if (oauth.getRequestToken(token) == null || oauth.getRequestToken(token).getConsumer() == null) {
            sb.append("Those temporary credentials do not exist");
        } else {
            sb.append("<h1>Resource Owner Authorization</h1>");
            sb.append("<form action='");
            sb.append(uriBuilder.clone().path("authorize").build());
            sb.append("' method='POST'>");
            sb.append("<input type='hidden' name='token' value='");
            sb.append(token);
            sb.append("'/>");
            sb.append("<label for='auth-select'>Approve Request:</label>");
            sb.append("<select id='auth-select' name='auth-select'>");
            sb.append("<option selected value='yes'>Yes</option>");
            sb.append("<option value='no'>No</option>");
            sb.append("</select>");
            sb.append("<input type='submit' value='Submit'/>");
            sb.append("</form>");
        }
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @Path("authorize")
    @POST
    public String handleAuthorizeForm(@FormParam("token") String token, @FormParam("auth-select") String approvedStatus) {
        DefaultOAuthProvider.Token tempCred = ((DefaultOAuthProvider) oauth).getRequestToken(token);
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>OAuth</title></head>");
        sb.append("<body>");
        String owner = secCtx.getUserPrincipal().getName();
        if (tempCred == null || tempCred.getConsumer() == null) {
            sb.append("Those temporary credentials do not exist");
        } else if (approvedStatus.equalsIgnoreCase("yes")) {
            Set<String> roles = new HashSet<>();
            roles.add("player");
            String verifier = ((DefaultOAuthProvider) oauth).authorizeToken(tempCred, secCtx.getUserPrincipal(), roles);
            sb.append("Your verification code is: ");
            sb.append(verifier);
        } else {
            sb.append("You denied the request");
        }
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}