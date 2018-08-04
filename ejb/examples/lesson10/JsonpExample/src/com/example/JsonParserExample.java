package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 *
 * @author mheimer
 */
public class JsonParserExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JsonParser jp = Json.createParser(new FileInputStream("data.json"));
        int depth = 0;
        while(jp.hasNext()) {
            Event e = jp.next();
            switch(e) {
                case START_OBJECT:
                    depth++;
                    break;
                case END_OBJECT:
                    depth--;
                    break;
                case START_ARRAY:
                    System.out.print("\n");
                    printSpaces(++depth);
                    System.out.print("LIST:");
                    break;
                case END_ARRAY:
                    depth--;
                    break;
                case KEY_NAME:
                    if(depth>1)System.out.println("");
                    printSpaces(depth);
                    System.out.print(jp.getString());
                    break;
                case VALUE_STRING:
                    System.out.print("=" + jp.getString());
                    break;
            }
        }
        System.out.println("");
    }
    
    public static void printSpaces(int count) {
        for(int i = 0; i < count - 1; i++) {
            System.out.print("\t");
        }
    }
}
