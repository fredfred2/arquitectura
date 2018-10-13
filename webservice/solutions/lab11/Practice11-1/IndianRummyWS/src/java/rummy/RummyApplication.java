package rummy;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class RummyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(IndianRummyRootResource.class);
        return s;
    }

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> set = new HashSet<>(1);
        try {
            JAXBContextResolver myJAXBContextResolver = new JAXBContextResolver();
            set.add(myJAXBContextResolver);
            set.add(new RummyExceptionMapper());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return set;
    }
}