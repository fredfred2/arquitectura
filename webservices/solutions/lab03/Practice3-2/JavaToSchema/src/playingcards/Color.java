package playingcards;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "colorType")
@XmlEnum
public enum Color {
    WHITE,
    RED,
    BLACK;
}