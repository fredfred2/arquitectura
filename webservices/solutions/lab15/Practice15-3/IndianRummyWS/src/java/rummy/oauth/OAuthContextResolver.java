package rummy.oauth;

import com.sun.jersey.oauth.server.api.providers.DefaultOAuthProvider;
import com.sun.jersey.oauth.server.spi.OAuthProvider;
import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class OAuthContextResolver extends SingletonTypeInjectableProvider<Context, OAuthProvider>{

    public OAuthContextResolver() {
        super(OAuthProvider.class, new DefaultOAuthProvider());
    }

}