package com.example.servlet;

import com.example.beans.ProductManager;
import com.example.model.Product;
import com.example.util.InventoryException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet(name = "UpdateInventory", urlPatterns = {"/UpdateInventory"})
public class UpdateInventory extends HttpServlet {

    private static final Logger logger = Logger.getLogger("com.example.servlet.UpdateInventory");

    @Inject
    private ProductManager manager;
    @Resource
    private UserTransaction ut;

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
            out.println("<title>UpdateInventory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Update JavaMart Inventory</h1>");
            out.println("<form method=\"post\">");
            out.println("<table><tbody>");
            out.println("<tr><td>Product ID:</td><td><input type=\"text\" name=\"id\"></td></tr>");
            out.println("<tr><td>Product Name:</td><td><input type=\"text\" name=\"name\"></td></tr>");
            out.println("<tr><td>Unit Price:</td><td><input type=\"text\" name=\"price\"></td></tr>");
            out.println("<tr><td>Description:</td><td><input type=\"text\" name=\"desc\"></td></tr>");
            out.println("<tr><td>Quantity:</td><td><input type=\"text\" name=\"quant\"></td></tr>");
            out.println("</tbody></table>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");

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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        double price = 0;
        int quant = 0;

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JavaMart PurchaseCart</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                price = Double.valueOf(request.getParameter("price"));
                quant = Integer.valueOf(request.getParameter("quant"));
            } catch (NumberFormatException ex) {
                out.println("Price and quantity must be numbers");
            }
            Product newProd = new Product(id, name, price, desc);
            out.println("Product: " + newProd + " Quantity: " + quant + "<br>");
            try {
                /* Note: we start a transaction here due to bug in GlassFish (GLASSFISH-21172)
                 * where the checked exception is lost unless a transaction is started before
                 * executing the transaction in the target CDI bean.
                 */
                ut.begin();
                manager.addNewProduct(newProd, quant);
                ut.commit();
            } catch (InventoryException e) {
                out.println("<p>Exception: " + e.getMessage() + "</p>");
                Throwable[] suppressed = e.getSuppressed();
                for (Throwable t : suppressed) {
                    out.println(t.getMessage() + "<br>");
                }
            } catch (Exception e) {
                out.println("<p>Unable to process inventory update, transaction rolled back - see log for more details</p>");
                logger.log(Level.INFO, e.getMessage());
            }
            out.println("<form action =\"" + request.getContextPath() + "/UpdateInventory\"><input type=\"submit\" value=\"Try Again\"></form>");
            out.println("</body>");
            out.println("</html>");
        }
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
