package schematojava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import jaxbcards.CardType;
import jaxbcards.StackType;

public class SchemaToJava {

    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance("jaxbcards");
            Unmarshaller u = jc.createUnmarshaller();
            InputStream in = new FileInputStream("src/cardstack.xml");
            JAXBElement<StackType> rootElement = (JAXBElement<StackType>) u.unmarshal(in);
            StackType cs = rootElement.getValue();
            List<CardType> cards = cs.getCard();
            for (CardType card : cards) {
                String rank = card.getRank();
                if (rank.equalsIgnoreCase("joker")) {
                    System.out.println(card.getColor() + " " + rank);
                } else {
                    System.out.println(rank + " of " + card.getSuit());
                }
            }
        } catch (JAXBException | FileNotFoundException ex) {
            ex.printStackTrace();
        }



    }
}