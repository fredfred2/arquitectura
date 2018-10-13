package util;

import cards.CardType;
import cards.ColorType;
import cards.SuitType;

public class CardUtils {

    public static ColorType getColor(CardType card) {
        if (card.getColor() != null) {
            return card.getColor();
        } else {
            if (card.getSuit() == SuitType.CLUBS || card.getSuit() == SuitType.SPADES) {
                return ColorType.BLACK;
            } else {
                return ColorType.RED;
            }
        }
    }

    public static String getUnicodeBack() {
        return new String(Character.toChars(0x1F0A0));
    }

    public static String toUnicode(CardType card) {
        if (card.getRank().equalsIgnoreCase("JOKER")) {
            if (card.getColor() == ColorType.WHITE || card.getColor() == ColorType.RED) {
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

        if (card.getSuit() == SuitType.CLUBS) {
            codePoint = clubBase;
        } else if (card.getSuit() == SuitType.DIAMONDS) {
            codePoint = diamondBase;
        } else if (card.getSuit() == SuitType.HEARTS) {
            codePoint = heartBase;
        } else if (card.getSuit() == SuitType.SPADES) {
            codePoint = spadeBase;
        }

        if (card.getRank().equalsIgnoreCase("A")) {
            codePoint += 0x1;
        } else if (card.getRank().equals("2")) {
            codePoint += 0x2;
        } else if (card.getRank().equals("3")) {
            codePoint += 0x3;
        } else if (card.getRank().equals("4")) {
            codePoint += 0x4;
        } else if (card.getRank().equals("5")) {
            codePoint += 0x5;
        } else if (card.getRank().equals("6")) {
            codePoint += 0x6;
        } else if (card.getRank().equals("7")) {
            codePoint += 0x7;
        } else if (card.getRank().equals("8")) {
            codePoint += 0x8;
        } else if (card.getRank().equals("9")) {
            codePoint += 0x9;
        } else if (card.getRank().equals("10")) {
            codePoint += 0xa;
        } else if (card.getRank().equalsIgnoreCase("J")) {
            codePoint += 0xb;
        } else if (card.getRank().equalsIgnoreCase("Q")) {
            codePoint += 0xd;
        } else if (card.getRank().equalsIgnoreCase("K")) {
            codePoint += 0xe;
        }

        return new String(Character.toChars(codePoint));
    }

    /**
     * Figures out which card is greater (Joker > Ace > King)
     *
     * @param rank1
     * @param rank2
     * @return 1 if the first card is greater, 0 if they are the same, -1 if the
     * second card is greater.
     */
    public static int compareCards(String rank1, String rank2) {
        int int1 = rankToOrderedCardValue(rank1);
        int int2 = rankToOrderedCardValue(rank2);
        return Integer.compare(int1, int2);
    }

    private static int rankToOrderedCardValue(String rank) {
        try {
            return Integer.parseInt(rank);
        } catch (Exception ex) {
            return faceToHighCardValue(rank);
        }
    }

    private static int faceToHighCardValue(String rank) {
        rank = rank.trim();
        switch (rank) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            case "JOKER":
                return 15;
        }
        return 0;
    }
}