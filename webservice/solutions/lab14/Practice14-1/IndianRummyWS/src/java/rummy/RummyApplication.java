package rummy;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;

public class RummyApplication extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(IndianRummyRootResource.class);
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
        set.add(new RummyExceptionMapper());
        return set;
    }
}