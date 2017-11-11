package com.example.web;

import com.example.ejb.ImageFacade;
import com.example.jsf.AuctionItemBean;
import com.example.util.ImageResizer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FileDownloadServlet", urlPatterns = {"/download/*"})
public class FileDownloadServlet extends HttpServlet {

    private int imageMaxSize = 120;
    @Inject
    private ImageFacade imageControl;
    @Inject
    private ThumbnailCacheBean tCache;
    @Inject
    private AuctionItemBean itemBean;

    // Called for both GET and POST requests
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        String mediaId = request.getPathInfo().replaceFirst("/", "");
        int imageId = new Integer(mediaId).intValue();
        //System.out.println("Media ID: " + imageId);

        try {
            String type;
            if (imageId == 0) {
                type = itemBean.getItem().getImage().getContentType();
            } else {
                type = imageControl.findImageById(imageId).getContentType();
            }
            //System.out.println("Mime type: " + type);
            response.setContentType(type);
            byte[] imageContent;
            if (request.getParameter("thumb") != null && ImageResizer.canResize(type)) {
                ServletOutputStream sout = response.getOutputStream();

                byte[] thumbBytes = tCache.getCachedThumbnail(mediaId);
                if (thumbBytes == null || imageId == 0) {
                    if (imageId == 0) {
                        imageContent = itemBean.getItem().getImage().getContent();
                    } else {
                        imageContent = imageControl.findImageById(imageId).getContent();
                    }
                    ByteArrayInputStream bais = new ByteArrayInputStream(imageContent);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageResizer.resize(imageMaxSize, bais, baos, type);
                    thumbBytes = baos.toByteArray();
                    tCache.setCachedThumbnail(mediaId, thumbBytes);
                }
                sout.write(thumbBytes);
            } else {
                if (imageId == 0) {
                    imageContent = itemBean.getItem().getImage().getContent();
                } else {
                    imageContent = imageControl.findImageById(imageId).getContent();
                }
                response.setContentLength(imageContent.length);
                ServletOutputStream sout = response.getOutputStream();
                sout.write(imageContent, 0, imageContent.length);
            }
        } catch (FileNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}