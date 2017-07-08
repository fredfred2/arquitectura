/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stub;

import com.example.model.Image;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create in-memory Images for use in the AuctionApplication
 * @author tmcginn
 */
public class StubbedImages {
    // Use displayName as the key

    private static ConcurrentHashMap<Integer, Image> images;
    private static int imageId = 2000;
    private static final StubbedImages instance = new StubbedImages();

    private StubbedImages() {
        images = new ConcurrentHashMap<>();

        // AntiquePhoneStand.jpg
        int id = StubbedImages.getNextId(); // 2000
        byte[] photo = null;
        int numBytes;
        Path p;
        try {
            p = FileSystems.getDefault().getPath("/apps/code/curso/jpa/resources/images/AntiquePhoneStand.jpg");
            numBytes = (int) Files.size(p);
            photo = new byte[numBytes];
            photo = Files.readAllBytes(p);
        } catch (IOException i) {
            System.out.println("IOException: " + i);
        }

        images.put(id, new Image(id, photo, "image/jpeg"));

        // AmericanGirlDoll.gif
        id = StubbedImages.getNextId();  // 2001
        photo = null;
        try {
            p = FileSystems.getDefault().getPath("/apps/code/curso/jpa/resources/images/Doll.jpg");
            numBytes = (int) Files.size(p);
            photo = new byte[numBytes];
            photo = Files.readAllBytes(p);
        } catch (IOException i) {
            System.out.println("IOException: " + i);
        }

        images.put(id, new Image(id, photo, "image/jpeg"));

        // AntiqueCoffeeGrinder.gif
        id = StubbedImages.getNextId();  // 2002
        photo = null;
        try {
            p = FileSystems.getDefault().getPath("/apps/code/curso/jpa/resources/images/AntiqueCoffeeGrinder.jpg");
            numBytes = (int) Files.size(p);
            photo = new byte[numBytes];
            photo = Files.readAllBytes(p);
        } catch (IOException i) {
            System.out.println("IOException: " + i);
        }

        images.put(id, new Image(id, photo, "image/jpeg"));


        // SaltPepperShakers.gif
        id = StubbedImages.getNextId();  // 2003
        photo = null;
        try {
            p = FileSystems.getDefault().getPath("/apps/code/curso/jpa/resources/images/SaltPepperShakers.jpg");
            numBytes = (int) Files.size(p);
            photo = new byte[numBytes];
            photo = Files.readAllBytes(p);
        } catch (IOException i) {
            System.out.println("IOException: " + i);
        }

        images.put(id, new Image(id, photo, "image/jpeg"));

        // LilkinzPolarBear.gif
        id = StubbedImages.getNextId();  // 2004
        photo = null;
        try {
            p = FileSystems.getDefault().getPath("/apps/code/curso/jpa/resources/images/PolarBear.jpg");
            numBytes = (int) Files.size(p);
            photo = new byte[numBytes];
            photo = Files.readAllBytes(p);
        } catch (IOException i) {
            System.out.println("IOException: " + i);
        }

        images.put(id, new Image(id, photo, "image/jpeg"));
        
                // Backpack.gif
        id = StubbedImages.getNextId();  // 2005
        photo = null;
        try {
            p = FileSystems.getDefault().getPath("/apps/code/curso/jpa/resources/images/Backpack.jpg");
            numBytes = (int) Files.size(p);
            photo = new byte[numBytes];
            photo = Files.readAllBytes(p);
        } catch (IOException i) {
            System.out.println("IOException: " + i);
        }

        images.put(id, new Image(id, photo, "image/jpeg"));
        
                        // HorseScupture.gif
        id = StubbedImages.getNextId();  // 2006
        photo = null;
        try {
            p = FileSystems.getDefault().getPath("/apps/code/curso/jpa/resources/images/HorseSculpture.jpg");
            numBytes = (int) Files.size(p);
            photo = new byte[numBytes];
            photo = Files.readAllBytes(p);
        } catch (IOException i) {
            System.out.println("IOException: " + i);
        }

        images.put(id, new Image(id, photo, "image/jpeg"));
    }

    public static ConcurrentHashMap<Integer, Image> getImages() {
        return images;
    }

    public static int getNextId() {
        return imageId++;
    }
}
