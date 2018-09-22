package playingcards;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "rankType")
@XmlEnum
public enum Rank {

    @XmlEnumValue("A")
    ACE("Ace", "A"),
    @XmlEnumValue("2")
    TWO("Two", "2"),
    @XmlEnumValue("3")
    THREE("Three", "3"),
    @XmlEnumValue("4")
    FOUR("Four", "4"),
    @XmlEnumValue("5")
    FIVE("Five", "5"),
    @XmlEnumValue("6")
    SIX("Six", "6"),
    @XmlEnumValue("7")
    SEVEN("Seven", "7"),
    @XmlEnumValue("8")
    EIGHT("Eight", "8"),
    @XmlEnumValue("9")
    NINE("Nine", "9"),
    @XmlEnumValue("10")
    TEN("Ten", "10"),
    @XmlEnumValue("J")
    JACK("Jack", "J"),
    @XmlEnumValue("Q")
    QUEEN("Queen", "Q"),
    @XmlEnumValue("K")
    KING("King", "K"),
    @XmlEnumValue("JOKER")
    JOKER("Joker", "Joker");
    private String text;
    private String abbreviation;

    private Rank(String text, String abbreviation) {
        this.text = text;
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
