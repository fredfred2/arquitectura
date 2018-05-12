package rummy.jaxb;

import cards.CardType;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"href", "cards"})
public class Hand {

    private String href;
    private List<CardType> cards;

    @XmlElement(required=true)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<CardType> getCards() {
        return cards;
    }

    public void setCards(List<CardType> cards) {
        this.cards = cards;
    }

}