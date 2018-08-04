package com.example;

import java.io.IOException;

/**
 *
 * @author mheimer
 */
public class MyApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("JsonGenerator:");
        JsonGeneratorExample.main(args);
        System.out.println("\nJsonWriter");
        JsonWriterExample.main(args);
        System.out.println("\nJsonParser:");
        JsonParserExample.main(args);
        System.out.println("\nJsonReader:");
        JsonReaderExample.main(args);
    }
    
}
