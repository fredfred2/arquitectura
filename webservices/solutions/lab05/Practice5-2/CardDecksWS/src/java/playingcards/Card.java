package playingcards;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "cardType", propOrder = {
    "rank",
    "suitOrColor"
})
public class Card {

    @XmlElement(required = true)
    private Rank rank;
    private Suit suit;
    private Color color;

    @XmlElements(value = {
        @XmlElement(name = "suit",
        type = Suit.class,
        required = true),
        @XmlElement(name = "color",
        type = Color.class,
        required = true)
    })
    private Object getSuitOrColor() {
        if (rank != null && rank == Rank.JOKER) {
            return color;
        } else {
            return suit;
        }
    }

    private void setSuitOrColor(Object object) {
        if (object instanceof Color) {
            color = (Color) object;
        } else if (object instanceof Suit) {
            suit = (Suit) object;
        }
    }
    
    public Card() { }

    /**
     * Creates a standard card in a traditional 52 card deck.
     *
     * @param rank The rank of the card, ace through king.
     * @param suit The suit of the card, clubs, diamonds, hearts, spades.
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Creates a black Joker card. Jokers are typically used as wildcards.
     */
    public static Card newBlackJoker() {
        Card card = new Card(Rank.JOKER, null);
        card.color = Color.BLACK;
        return card;
    }

    /**
     * Creates a red Joker card. Jokers are typically used as wildcards.
     */
    public static Card newRedJoker() {
        Card card = new Card(Rank.JOKER, null);
        card.color = Color.RED;
        return card;
    }

    public static String getUnicodeBack() {
        return new String(Character.toChars(0x1F0A0));
    }

    @Override
    public String toString() {
        if (rank == Rank.JOKER) {
            if (color.equals(Color.WHITE)) {
                return "White " + rank.toString();
            } else if (color.equals(Color.RED)) {
                return "Red " + rank.toString();
            } else {
                return "Black " + rank.toString();
            }
        }
        return rank.toString() + " of " + suit.toString();
    }

    public String toAbbreviation() {
        if (rank == Rank.JOKER) {
            return rank.getAbbreviation();
        } else {
            return rank.getAbbreviation() + suit.getAbbreviation();
        }
    }

    public String toUnicodeAbbreviation() {
        if (rank == Rank.JOKER) {
            return rank.getAbbreviation();
        } else {
            return rank.getAbbreviation() + suit.toUnicode();
        }
    }

    public String toUnicode() {
        if (rank == Rank.JOKER) {
            if (color.equals(Color.WHITE) || color.equals(Color.RED)) {
                return new String(Character.toChars(0x1F0DF));
            } else {
                return new String(Character.toChars(0x1F0CF));
            }
        }

        final int clubBase = 0x1F0D0;
        final int diamondBase = 0x1F0C0;
        final int heartBase = 0x1F0B0;
        final int spadeBase = 0x1F0A0;

        int codePoint = 0;

        if (suit == Suit.CLUBS) {
            codePoint = clubBase;
        } else if (suit == Suit.DIAMONDS) {
            codePoint = diamondBase;
        } else if (suit == Suit.HEARTS) {
            codePoint = heartBase;
        } else if (suit == Suit.SPADES) {
            codePoint = spadeBase;
        }

        if (rank == Rank.ACE) {
            codePoint += 0x1;
        } else if (rank == Rank.TWO) {
            codePoint += 0x2;
        } else if (rank == Rank.THREE) {
            codePoint += 0x3;
        } else if (rank == Rank.FOUR) {
            codePoint += 0x4;
        } else if (rank == Rank.FIVE) {
            codePoint += 0x5;
        } else if (rank == Rank.SIX) {
            codePoint += 0x6;
        } else if (rank == Rank.SEVEN) {
            codePoint += 0x7;
        } else if (rank == Rank.EIGHT) {
            codePoint += 0x8;
        } else if (rank == Rank.NINE) {
            codePoint += 0x9;
        } else if (rank == Rank.TEN) {
            codePoint += 0xa;
        } else if (rank == Rank.JACK) {
            codePoint += 0xb;
        } else if (rank == Rank.QUEEN) {
            codePoint += 0xd;
        } else if (rank == Rank.KING) {
            codePoint += 0xe;
        }

        return new String(Character.toChars(codePoint));
    }
}
