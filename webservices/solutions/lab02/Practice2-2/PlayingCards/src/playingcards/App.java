package playingcards;

public class App {

    public static void main(String[] args) {
        FrenchCardDeck deck = new FrenchCardDeck(2);
        deck.show();
        deck.shuffle();
        deck.show();
    }
}