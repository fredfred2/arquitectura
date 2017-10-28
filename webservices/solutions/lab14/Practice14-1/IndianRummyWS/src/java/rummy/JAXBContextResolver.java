package rummy;

import cards.CardType;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import rummy.jaxb.Hand;
import rummy.jaxb.Link;
import rummy.jaxb.Player;
import rummy.jaxb.PlayerStats;
import rummy.jaxb.RummyGame;

@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

    private final JAXBContext context;
    private Class[] types = {Hand.class,
        Link.class,
        Player.class,
        PlayerStats.class,
        RummyGame.class,
        CardType.class};

    public JAXBContextResolver() throws Exception {
        JSONConfiguration jsonConfig = JSONConfiguration.natural()
                .rootUnwrapping(true)
                .humanReadableFormatting(true)
                .build();
        context = new JSONJAXBContext(jsonConfig, types);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        return context;
    }
}