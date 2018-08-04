package com.example.media;

import java.awt.Graphics2D;
import static java.awt.RenderingHints.KEY_INTERPOLATION;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

public class ImageResizer {

    public static boolean canResize(String mimeType) {
        for (String supportedType : ImageIO.getWriterMIMETypes()) {
            if (supportedType.equalsIgnoreCase(mimeType)) {
                return true;
            }
        }
        return false;
    }

    public static void resize(int maxSize, InputStream in, OutputStream out, String mimeType) throws IOException {
        BufferedImage originalImage = ImageIO.read(in);
        int newWidth, newHeight;
        if (originalImage.getWidth() <= maxSize && originalImage.getHeight() <= maxSize) {
            newWidth = originalImage.getWidth();
            newHeight = originalImage.getHeight();
        } else if(originalImage.getWidth() > originalImage.getHeight()) {
            newWidth = maxSize;
            newHeight = newWidth * originalImage.getHeight() / originalImage.getWidth();
        } else {
            newHeight = maxSize;
            newWidth = newHeight * originalImage.getWidth() / originalImage.getHeight();
        }
        int imgType = originalImage.getType();
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, imgType);
        Graphics2D g2d = (Graphics2D) newImage.getGraphics();
        g2d.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        String shortType = mimeType.replaceFirst("image/", "");
        ImageIO.write(newImage, shortType, out);
    }
}