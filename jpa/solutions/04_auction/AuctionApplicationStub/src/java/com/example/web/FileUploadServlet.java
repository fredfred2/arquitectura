package com.example.web;

import com.example.ejb.ImageFacade;
import com.example.jsf.AuctionItemBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/upload.do"})
@MultipartConfig()
public class FileUploadServlet extends HttpServlet {

    @Inject
    private ImageFacade imageControl;
    @Inject
    private AuctionItemBean itemBean;

    // Called for both GET and POST requests
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int imageId = 0;
        // Determine if this in fact a multipart/form-data post    
        //Part myPart = request.getPart("file1");

        Collection<Part> parts;
        try {
            parts = request.getParts();
        } catch (ServletException se) {
            request.setAttribute("errorMessage", "Not a multipart/form-data request");
            RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
            rd.forward(request, response);
            return;
        }

        if (parts.isEmpty()) {
            request.setAttribute("errorMessage", "No parts in multipart/form-data request");
            RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
            rd.forward(request, response);
        } else {
            // Write the files to the location directory
            List<String> warnings = new ArrayList<>();
            request.setAttribute("warnings", warnings);
            List<String> items = new ArrayList<>();
            request.setAttribute("mediaItems", items);
            for (Part part : parts) {
                // Get the name of the file from the content-disposition header
                String disposition = part.getHeader("content-disposition");
                String name = disposition.substring(disposition.indexOf("filename") + 10, disposition.length() - 1);
                try {
                    String contentType = getServletContext().getMimeType(name);
                    //if (contentType.startsWith("image") || contentType.startsWith("video")) {
                    if (contentType != null && contentType.startsWith("image")) {
                        //imageId = imageControl.addImageContent(part.getInputStream(), contentType);
                        itemBean.addImageContent(part.getInputStream(), contentType);
                    } else {
                        //warnings.add(contentType + " not supported for " + name);
                        request.setAttribute("errorMessage", "File type not supported: " + name);
                        RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
                        rd.forward(request, response);
                    }
                    part.delete();
                } catch (IOException fe) {
                    warnings.add("Exception writing file: " + name + " : " + fe);
                }
                //RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
                //System.out.println("Successfully loaded image with id: "+imageId+" - returning");
                RequestDispatcher rd = request.getRequestDispatcher("/faces/createAuction.xhtml?imageId="+imageId);
                rd.forward(request, response);
            }
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