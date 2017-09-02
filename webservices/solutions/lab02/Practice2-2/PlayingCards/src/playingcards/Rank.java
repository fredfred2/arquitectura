package playingcards;

public enum Rank {
    ACE("Ace", "A"),
    TWO("Two", "2"),
    THREE("Three", "3"),
    FOUR("Four", "4"),
    FIVE("Five", "5"),
    SIX("Six", "6"),
    SEVEN("Seven", "7"),
    EIGHT("Eight", "8"),
    NINE("Nine", "9"),
    TEN("Ten", "10"),
    JACK("Jack", "J"),
    QUEEN("Queen", "Q"),
    KING("King", "K"),
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
