package com.example;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author mheimer
 */
public class JsonWriterExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JsonWriter jw = Json.createWriter(System.out);

        JsonObject address = Json.createObjectBuilder()
                .add("street", "221b Baker Street")
                .add("city", "London")
                .add("country", "England")
                .build();

        JsonObject sherlock = Json.createObjectBuilder()
                .add("firstName", "Sherlock")
                .add("lastName", "Holmes")
                .add("address", address)
                .build();

        JsonObject watson = Json.createObjectBuilder()
                .add("firstName", "John")
                .add("lastName", "Watson")
                .add("address", address)
                .build();

        JsonArray persons = Json.createArrayBuilder()
                .add(sherlock)
                .add(watson)
                .build();

        JsonObject root = Json.createObjectBuilder()
                .add("persons", persons)
                .build();

        jw.write(root);
        jw.close();
    }
    
}
