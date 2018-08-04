package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author mheimer
 */
public class JsonReaderExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JsonReader jr = Json.createReader(new FileInputStream("data.json"));
        JsonObject root = jr.readObject();
        System.out.println("persons");
        JsonArray persons = root.getJsonArray("persons");
        if (persons != null) {
            System.out.println("\tLIST:");
            for (JsonValue value : persons) {
                if (value.getValueType() == JsonValue.ValueType.OBJECT) {
                    JsonObject person = (JsonObject) value;
                    System.out.println("\t\tfirstName=" + person.getString("firstName"));
                    System.out.println("\t\tlastName=" + person.getString("lastName"));
                    JsonObject address = person.getJsonObject("address");
                    if (address != null) {
                        System.out.println("\t\taddress");
                        System.out.println("\t\t\tstreet=" + address.getString("street"));
                        System.out.println("\t\t\tcity=" + address.getString("city"));
                        System.out.println("\t\t\tcountry=" + address.getString("country"));
                    }
                }
            }
        }
    }
}
