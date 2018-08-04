package com.example.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author mheimer
 */
@ApplicationPath("/resources")
public class HotSpotApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(HotSpotResource.class);
        return classes;
    }
    
    
    
}
