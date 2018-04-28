package javatoschema;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import playingcards.CardCollection;
import playingcards.FrenchCardDeck;

public class JavaToSchema {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance("playingcards");

            context.generateSchema(new SchemaOutputResolver() {
                @Override
                public Result createOutput(String namespaceUri, String fileName) throws IOException {
                    return new StreamResult(new File("src/cardschema.xsd"));
                }
            });

            CardCollection deck = new FrenchCardDeck();
            deck.shuffle();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "cardschema.xsd");
            m.marshal(deck, new File("src/cardstack.xml"));
        } catch (JAXBException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
