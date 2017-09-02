package playingcards;

/**
 * This class represents a standard 52 card deck with the possible inclusion of
 * jokers. The difference between this class and the parent CardCollection is
 * that objects created from this class will always contain at least 52 cards
 * which use the Clubs, Diamonds, Hearts, and Spades suits and the ranks of
 * 2,3,4,5,6,7,8,9,10,J,Q,K,A.
 *
 * @author mheimer
 */
public class FrenchCardDeck extends CardCollection {

    public FrenchCardDeck() {
        populateDeck(52);
    }

    public FrenchCardDeck(int jokerCount) {
        populateDeck(52 + jokerCount);
    }

    // always building at least 52 cards
    private void populateDeck(int count) {
        assert (count >= 52);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                if (rank == Rank.JOKER) {
                    continue;
                } else {
                    addCards(new Card(rank, suit));
                }
            }
        }
        if (count > 52) {
            addCards(Card.newRedJoker());
        }

        while (getCardCount() < count) {
            addCards(Card.newBlackJoker());
        }
    }
}