package rummy.jaxb;

import cards.CardType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "rummy-game")
@XmlType(propOrder = {"players", "currentTurnPlayer", "topDiscardCard", "href"})
@XmlAccessorType(XmlAccessType.NONE)
public class RummyGame {

    @XmlElement(name = "player")
    private List<Player> players = new ArrayList<>();
    // not required when creating a game
    @XmlElement(name = "whos-turn")
    private String currentTurnPlayer;
    // not required when creating a game
    @XmlElement(name = "discard-pile-top-card")
    private CardType topDiscardCard;
    // not required when creating a game
    @XmlElement(name = "href")
    private String href;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public void setCurrentTurnPlayer(String currentTurnPlayer) {
        this.currentTurnPlayer = currentTurnPlayer;
    }

    public CardType getTopDiscardCard() {
        return topDiscardCard;
    }

    public void setTopDiscardCard(CardType topDiscardCard) {
        this.topDiscardCard = topDiscardCard;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}