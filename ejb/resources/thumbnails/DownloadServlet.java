package com.example.controller;

import com.example.bean.FileMediaBean;
import com.example.media.ImageResizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DownloadServlet", urlPatterns = {"/download/*"})
public class DownloadServlet extends HttpServlet {

    // Called for both GET and POST requests
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        String realPath = context.getRealPath("fxmedia");
        FileMediaBean fmm = new FileMediaBean(realPath);
        //ServletContext ctx = getServletContext();
        String mediaId = request.getPathInfo().replaceFirst("/", "");

        try {
            String type = context.getMimeType(mediaId);
            response.setContentType(type);
            File f = new File(realPath + "/" + mediaId);
            if (request.getParameter("thumb") != null && ImageResizer.canResize(type)) {
                ServletOutputStream sout = response.getOutputStream();
                ImageResizer.resize(120, new FileInputStream(f), sout, type);
            } else {
                response.setContentLength((int) f.length());
                ServletOutputStream sout = response.getOutputStream();
                Files.copy(f.toPath(), sout);
            }
        } catch (FileNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

//    @Override
//    public long getLastModified(HttpServletRequest request) {
//        String mediaId = request.getPathInfo().replaceFirst("/", "");
//        try {
//            MediaItem mediaItem = mm.getMediaItem(mediaId);
//            //must be rounded down to the nearest second
//            return mediaItem.getDate().getTime() / 1000 * 1000;
//        } catch (FileNotFoundException e) {
//            return -1;
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
