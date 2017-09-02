package playingcards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to represent a stack of playing cards.
 * @author mheimer
 */
public class CardCollection {

    public static final int DISPLAY_ABBREVIATION = 0;
    public static final int DISPLAY_UNICODE_SUIT = 1;
    public static final int DISPLAY_DESCRIPTION = 2;
    public static final int DISPLAY_UNICODE6 = 3;
    private List<Card> cards = new ArrayList<>();

    public void addCards(CardCollection cardCollection) {
        cards.addAll(cardCollection.cards);
    }

    public void addCards(Card... cardArray) {
        cards.addAll(Arrays.asList(cardArray));
    }

    public Card takeCard() {
        return cards.remove(0);
    }

    public Card[] takeCards(int count) throws IllegalArgumentException {
        if (count > cards.size()) {
            throw new IllegalArgumentException("Not enough cards. Have " + cards.size() + ", requested " + count);
        } else {
            Card[] result = new Card[count];
            for (int i = 0; i < count; i++) {
                result[i] = cards.remove(0);
            }
            return result;
        }
    }

    public int getCardCount() {
        return cards.size();
    }

    /**
     * Shuffle an array of cards. Shuffling is done using the Durstenfeld
     * algorithm which is based on the Fisher-Yates method
     *
     * @param cards
     */
    public void shuffle() {
        ThreadLocalRandom tlRandom = ThreadLocalRandom.current();
        for (int topIndex = cards.size() - 1; topIndex > 0; topIndex--) {
            int randIndex = tlRandom.nextInt(topIndex + 1);
            Card tempCard = cards.get(randIndex);
            cards.set(randIndex, cards.get(topIndex));
            cards.set(topIndex, tempCard);
        }
    }

    public void show() {
        int displayType = DISPLAY_UNICODE_SUIT;

        for (Card card : cards) {
            switch (displayType) {
                case DISPLAY_DESCRIPTION:
                    System.out.println(card.toString());
                    break;
                case DISPLAY_UNICODE6:
                    System.out.print(card.toUnicode());
                    break;
                case DISPLAY_UNICODE_SUIT:
                    System.out.print(card.toUnicodeAbbreviation() + " ");
                    break;
                case DISPLAY_ABBREVIATION:
                    System.out.print(card.toAbbreviation() + " ");
                    break;
            }
        }
        if (displayType == DISPLAY_UNICODE6 || displayType == DISPLAY_UNICODE_SUIT || displayType == DISPLAY_ABBREVIATION) {
            System.out.println("");
        }
    }
}