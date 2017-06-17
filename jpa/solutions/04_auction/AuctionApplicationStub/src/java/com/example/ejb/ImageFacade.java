package com.example.ejb;

import com.example.model.Image;
import com.example.stub.StubbedImages;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class ImageFacade {

    private static final Logger LOG = Logger.getLogger(ImageFacade.class.getName());

    public ImageFacade() {
    }

    public Image findImageById(int imageId) {
        ConcurrentHashMap<Integer, Image> images = StubbedImages.getImages();
        Image image = images.get(imageId);
        return image;
    }

//    public String getImageContentType(int imageId) {
//        String type = null;
//        Image image = findImageById(imageId);
//        if (image != null) {
//            type = image.getContentType();
//        }
//        return type;
//    }
//
//    public byte[] getImageContent(int imageId) {
//        byte[] photo = null;
//        Image image = findImageById(imageId);
//        if (image != null) {
//            photo = image.getContent();
//        }
//        return photo;
//    }

    // Return the imageId or 0 for a failure
//    public int addImageContent(InputStream content, String contentType) throws IOException {
//        int imageId;
//        ConcurrentHashMap<Integer, Image> images = StubbedImages.getImages();
//        int bytesRead = 0;
//        byte[] photo;
//        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
//            byte[] buffer = new byte[8192]; // 8K blocks
//            while ((bytesRead = content.read(buffer, 0, buffer.length)) != -1) {
//                bos.write(buffer, 0, bytesRead);
//            }
//            photo = bos.toByteArray();
//            imageId = StubbedImages.getNextId();
//            Image image = new Image(imageId, photo, contentType);
//            images.put(imageId, image);
//        }
//        return imageId;
//    }
    
    public Image addImage(Image image) {
        ConcurrentHashMap<Integer, Image> images = StubbedImages.getImages();
        int imageId = StubbedImages.getNextId();
        image.setImageId(imageId);
        images.put(imageId, image);
        return image;
    }

    public void removeImage(Image image) {
        ConcurrentHashMap<Integer, Image> images = StubbedImages.getImages();
        images.remove(image.getImageId());
    }
}