package com.example.servlet;

import com.example.beans.ProductManager;
import com.example.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet(name = "BatchUpdateInventory", urlPatterns = {"/BatchUpdateInventory"})
public class BatchUpdateInventory extends HttpServlet {

    private static final Logger logger = Logger.getLogger("com.example.servlet.UpdateInventory");

    @Inject
    private ProductManager manager;
    @Resource
    private UserTransaction ut;

    private final Product[] newProducts = {
        // Failed record: empty id, too short product name
        new Product("", "Empty ID", 12.34, "This is a product with no ID"),
        // Failed record: null product name
        new Product("DUKEHAT", null, 25.99, "A baseball cap with a Duke logo!"),
        // Failed record: price too low
        new Product("JAVAKIDS", "Kids Java Logo T-Shirt", 4.99, "A kid-sized T-shirt with a Java Logo"),
        // Passed record
        new Product("JAVAJACKET-L", "Ladies Java Jacket", 104.90, "A stylish Java Jacket for ladies - specify size")
    };

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>BatchUpdateInventory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Update JavaMart Inventory (batch)</h1>");
            out.println("<p>Attempting to create the following products:</p>");

            for (Product p : newProducts) {
                out.println("Product: <br>");
                out.println("  ID:    " + p.getId() + "<br>");
                out.println("  Name:  " + p.getName() + "<br>");
                out.println("  Price: " + p.getPrice() + "<br>");
                out.println("  Desc:  " + p.getDescription() + "<br>");
                List<String> messages = manager.validateProduct(p);
                if (!messages.isEmpty()) {
                    for (String message : messages) {
                        out.println("<br>Failure: " + message);
                    }
                } else {
                    out.println("<br/>Success!");
                }
                out.println("<p/>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
