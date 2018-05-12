package rummy;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import rummy.oauth.OAuthContextResolver;
import rummy.oauth.OAuthRootResource;

@ApplicationPath("resources")
public class RummyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(IndianRummyRootResource.class);
        s.add(OAuthRootResource.class);
        s.add(com.sun.jersey.oauth.server.api.resources.AccessTokenRequest.class);
        s.add(com.sun.jersey.oauth.server.api.resources.RequestTokenRequest.class);
        return s;
    }

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> set = new HashSet<>(2);
        try {
            JAXBContextResolver myJAXBContextResolver = new JAXBContextResolver();
            set.add(myJAXBContextResolver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        set.add(new OAuthContextResolver());
        set.add(new RummyExceptionMapper());
        return set;
    }
}