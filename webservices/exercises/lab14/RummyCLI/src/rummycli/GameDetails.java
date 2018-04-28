package rummycli;

import java.util.ArrayList;
import java.util.List;

public class GameDetails extends GameSummary {

    private String discardTopCard;
    private List<String> myHand = new ArrayList<>();
    private String version;

    public List<String> getMyHand() {
        return myHand;
    }

    public void setMyHand(List<String> myHand) {
        this.myHand = myHand;
    }

    public void addCardToHand(String card) {
        myHand.add(card);
    }

    public String getDiscardTopCard() {
        return discardTopCard;
    }

    public void setDiscardTopCard(String discardTopCard) {
        this.discardTopCard = discardTopCard;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder gameDetails = new StringBuilder();
        gameDetails.append(super.toString());
        gameDetails.append("\n");
        gameDetails.append("Discard: ");
        gameDetails.append(toUnicode(discardTopCard));
        gameDetails.append(", Stock: ");
        gameDetails.append(getUnicodeBack());
        gameDetails.append("\n");

        for (String card : myHand) {
            gameDetails.append(toUnicode(card));
        }
        return gameDetails.toString();
    }

    public static String getUnicodeBack() {
        return new String(Character.toChars(0x1F0A0));
    }

    /**
     * Converts the two character shorthand of a playing into a UNICODE char.
     * Assumes input in the form of JH, 2C, 10D, AS. One letter rank followed by
     * one letter suit except Jokers which are either RJ or BJ. R = Red, B =
     * Black.
     */
    public static String toUnicode(String card) {
        card = card.toUpperCase();
        if (card.startsWith("10") && card.length() == 3) {
            card = "1" + card.charAt(2);
        }
        if (card.length() != 2) {
            throw new IllegalArgumentException("Invalid card shorthand: " + card);
        }
        if (card.charAt(1) == 'J') {
            if (card.charAt(0) == 'R') {
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

        if (card.charAt(1) == 'C') {
            codePoint = clubBase;
        } else if (card.charAt(1) == 'D') {
            codePoint = diamondBase;
        } else if (card.charAt(1) == 'H') {
            codePoint = heartBase;
        } else if (card.charAt(1) == 'S') {
            codePoint = spadeBase;
        }

        if (card.charAt(0) == 'A') {
            codePoint += 0x1;
        } else if (card.charAt(0) == '2') {
            codePoint += 0x2;
        } else if (card.charAt(0) == '3') {
            codePoint += 0x3;
        } else if (card.charAt(0) == '4') {
            codePoint += 0x4;
        } else if (card.charAt(0) == '5') {
            codePoint += 0x5;
        } else if (card.charAt(0) == '6') {
            codePoint += 0x6;
        } else if (card.charAt(0) == '7') {
            codePoint += 0x7;
        } else if (card.charAt(0) == '8') {
            codePoint += 0x8;
        } else if (card.charAt(0) == '9') {
            codePoint += 0x9;
        } else if (card.charAt(0) == '1') {
            codePoint += 0xa;
        } else if (card.charAt(0) == 'J') {
            codePoint += 0xb;
        } else if (card.charAt(0) == 'Q') {
            codePoint += 0xd;
        } else if (card.charAt(0) == 'K') {
            codePoint += 0xe;
        }

        return new String(Character.toChars(codePoint));
    }
}