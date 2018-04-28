package carddeckseclient;

import cards.CardStack;
import cards.CardType;
import java.util.List;

public class CardDeckSEClient {

    public static void main(String[] args) {
//        CardDeckSessionBeanService service = new CardDeckSessionBeanService();
//        CardDeckSessionBean port = service.getCardDeckSessionBeanPort();
//        int deckId1 = port.createDeck(0);
//        port.shuffleDeck(deckId1);
//        StackType deck = port.getDeck(deckId1);

        int deckId1 = createDeck(0);
        shuffleDeck(deckId1);
        CardStack deck = getDeck(deckId1);

        List<CardType> cards = deck.getCards();

        for (CardType card : cards) {
            if (card.getRank().equalsIgnoreCase("JOKER")) {
                System.out.println(card.getColor() + " " + card.getRank());
            } else {
                System.out.println(card.getRank() + " of " + card.getSuit());
            }
        }
    }

    private static boolean shuffleDeck(int arg0) {
        cards.CardDeckSessionBeanService service = new cards.CardDeckSessionBeanService();
        cards.CardDeckSessionBean port = service.getCardDeckSessionBeanPort();
        return port.shuffleDeck(arg0);
    }

    private static Integer createDeck(int arg0) {
        cards.CardDeckSessionBeanService service = new cards.CardDeckSessionBeanService();
        cards.CardDeckSessionBean port = service.getCardDeckSessionBeanPort();
        return port.createDeck(arg0);
    }

    private static CardStack getDeck(int arg0) {
        cards.CardDeckSessionBeanService service = new cards.CardDeckSessionBeanService();
        cards.CardDeckSessionBean port = service.getCardDeckSessionBeanPort();
        return port.getDeck(arg0);
    }
}