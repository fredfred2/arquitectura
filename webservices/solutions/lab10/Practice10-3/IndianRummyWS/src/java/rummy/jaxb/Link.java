package rummy.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="link")
@XmlType(propOrder={"href", "rel"})
public class Link {

    @XmlAttribute(required=true)
    public String href;
    @XmlAttribute(required=true)
    public String rel;

}