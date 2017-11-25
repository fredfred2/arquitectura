package com.example.ejb;

import com.example.model.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ImageFacade {

    // added
    @PersistenceContext(unitName = "AuctionPU")
    private EntityManager em;
    private static final Logger LOG = Logger.getLogger(ImageFacade.class.getName());

    public ImageFacade() {
    }

    public Image findImageById(int imageId) {
        Image image = null;
        try {
            image = em.find(Image.class, imageId);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
        return image;
    }
}