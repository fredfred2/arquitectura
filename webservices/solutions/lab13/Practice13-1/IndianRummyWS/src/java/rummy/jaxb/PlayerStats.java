package rummy.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "stats")
@XmlType(propOrder = {"wins", "losses"})
public class PlayerStats {

    @XmlElement(required = true)
    public int wins;
    @XmlElement(required = true)
    public int losses;
}