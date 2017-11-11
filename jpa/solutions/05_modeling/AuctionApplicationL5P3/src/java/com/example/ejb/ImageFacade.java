package com.example.ejb;

import com.example.model.Image;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class ImageFacade {

    private static final Logger LOG = Logger.getLogger(ImageFacade.class.getName());

    public ImageFacade() {
    }

    public Image findImageById(int imageId) {
        Image image = null;
        // Practice 6-1 code goes here

        return image;
    }
}