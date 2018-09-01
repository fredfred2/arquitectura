package com.example.servlet;

import com.example.dao.InventoryDAO;
import com.example.dao.ProductDAO;
import com.example.beans.ShoppingCart;
import com.example.model.Inventory;
import com.example.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "JavaMart", urlPatterns = {"/JavaMart"})
public class JavaMart extends HttpServlet {

    @Inject
    private ProductDAO productDAO;
    @Inject
    private InventoryDAO inventoryDAO;
    @Inject
    private ShoppingCart cart;

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
        String context = request.getContextPath();
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JavaMart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome to JavaMart!</h1>");
            try {
                List<Product> products = productDAO.getAllProducts();
                displayProducts(context, out, products);
                out.println("<p>Items in cart: " + cart.getItemCount());
            } catch (SQLException ex) {
                out.println("SQLException: " + ex);
            }
            out.println("<form action=" + context + "/PurchaseCart><input type=\"submit\" value=\"Buy Cart\">");
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
        String id = request.getParameter("id");
        if (id != null) {
            cart.addItem(id, 1);
        }
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

    private void displayProducts(String context, PrintWriter out, List<Product> products) throws SQLException {
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<td>ID</td>");
        out.println("<td>Name</td>");
        out.println("<td>Description</td>");
        out.println("<td>Price</td>");
        out.println("<td>Available</td>");
        out.println("<td>Action</td>");
        out.println("</tr>");
        for (Product p : products) {
            Inventory i = inventoryDAO.getInventory(p.getId());
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getName() + "</td>");
            out.println("<td>" + p.getDescription() + "</td>");
            out.println("<td>" + p.getPrice() + "</td>");
            out.println("<td>" + i.getQuantity_on_hand() + "</td>");
            if (i.getQuantity_on_hand() > 0) {
                out.println("<td><a href=\"" + context + "/JavaMart?id=" + p.getId() + "\">Buy</a></td>");
            } else {
                out.println("<td style=\"color:red\">Out of Stock</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
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
