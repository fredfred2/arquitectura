package com.example;

import com.example.rest.MediaList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class MediaTestInteractive {
    
    private static String[] mediaArray;

    public static void main(String[] args) {
        // Create a WebTarget object for our RESTful calls
        String baseURL = "http://localhost:8080/WebMediaManager/resources";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURL);

        boolean timeToQuit = false;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                timeToQuit = executeMenu(in, target);
            } while (!timeToQuit);
        } catch (IOException e) {
            System.out.println("Error " + e.getClass().getName() + " , quiting.");
            System.out.println("Message: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error closing resource " + e.getClass().getName());
            System.out.println("Message: " + e.getMessage());
        }
    }

    public static boolean executeMenu(BufferedReader in, WebTarget target) throws IOException {
        MediaList list;
        String action;
        int id;

        System.out.println("\n\n[L]ist | [R]ead | [A]dd | [D]elete | [Q]uit: ");
        action = in.readLine();
        if (action.length() == 0) {
            System.out.println("Enter one of: L, R, D, A, Q");
            return false;
        } else if (action.toUpperCase().charAt(0) == 'Q') {
            return true;
        }

        switch (action.toUpperCase().charAt(0)) {
            // List all the media resources available using GET
            case 'L':
                // TODO: write the code to list all of the media in the collection
                break;

            // Display a media element (metadata) using a GET 
            case 'R':
                System.out.println("Enter media number to read: ");
                id = new Integer(in.readLine().trim());

                // TODO: write the code to display a media item
                break;

            // Delete a media item using DELETE
            case 'D':
                System.out.println("Enter media number to delete: ");
                id = new Integer(in.readLine().trim());

                // TODO: write the code to delete a media item
                break;

            // Add a new media item using a PUT 
            case 'A':
                System.out.println("Enter full path to new media item: ");
                String path = in.readLine();

                // TODO: Write the code to add a new media item to the collection
                break;

            default:
                System.out.println("Enter one of: L, R, D, A, Q");
        }

        return false;
    }
}
