package rummy.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"userName", "stats", "password", "href", "hand"})
public class Player {

    private String userName;
    private PlayerStats stats;
    private String password;
    private String href;
    private Hand hand;

    @XmlElement(required=true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    // not required when creating or updating, read-only
    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }
    // not required when reading
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // not required when creating or updating, read-only
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return userName;
    }

}