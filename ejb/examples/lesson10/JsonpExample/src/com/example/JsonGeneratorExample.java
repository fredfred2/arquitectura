package com.example;

import javax.json.Json;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author mheimer
 */
public class JsonGeneratorExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JsonGenerator jgen = Json.createGenerator(System.out);
        jgen.writeStartObject()
                .writeStartArray("persons")
                    .writeStartObject()
                        .write("firstName", "Sherlock")
                        .write("lastName", "Holmes")
                        .writeStartObject("address")
                            .write("street", "221b Baker Street")
                            .write("city", "London")
                            .write("country", "England")
                        .writeEnd()
                    .writeEnd()
                    .writeStartObject()
                        .write("firstName", "John")
                        .write("lastName", "Watson")
                        .writeStartObject("address")
                            .write("street", "221b Baker Street")
                            .write("city", "London")
                            .write("country", "England")
                        .writeEnd()
                    .writeEnd()
                .writeEnd()
        .writeEnd();
        jgen.flush();
    }
    
}
