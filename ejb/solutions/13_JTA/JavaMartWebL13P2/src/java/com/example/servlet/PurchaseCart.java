package com.example.servlet;

import com.example.beans.ShoppingCart;
import com.example.model.Product;
import com.example.util.PurchaseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
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

@WebServlet(name = "PurchaseCart", urlPatterns = {"/PurchaseCart"})
public class PurchaseCart extends HttpServlet {

    private static final Logger logger = Logger.getLogger("com.example.servlet.PurchaseCart");
    @Inject
    private ShoppingCart shoppingCart;
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
            out.println("<title>JavaMart PurchaseCart</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                displayProductSalesCart(out);
            } catch (SQLException ex) {
                out.println("<p>" + ex + "</p>");
            }
            out.println("<form method=\"post\">");
            out.println("<input type=\"submit\" name=\"action\" value=\"Purchase\">");
            out.println("<input type=\"submit\" name=\"action\" value=\"Cancel\">");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void displayProductSalesCart(PrintWriter out) throws SQLException {
        out.println("<h2>Your Cart Contains:</h2>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<td>ID</td>");
        out.println("<td>Name</td>");
        out.println("<td>Description</td>");
        out.println("<td>Price</td>");
        out.println("<td>Ordered</td>");
        out.println("<td>SubTotal</td>");
        out.println("</tr>");
        Map<Product, Integer> cart = shoppingCart.getCart();
        Set<Product> products = cart.keySet();
        double totalSale = 0, subTotal = 0;
        for (Product p : products) {
            //Product p = productDAO.getProductById(pSales.getProduct_id());
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getName() + "</td>");
            out.println("<td>" + p.getDescription() + "</td>");
            out.println("<td>" + p.getPrice() + "</td>");
            out.println("<td>" + cart.get(p) + "</td>");
            subTotal = cart.get(p) * p.getPrice();
            out.println("<td>" + subTotal + "</td>");
            out.println("</tr>");
            totalSale += subTotal;
        }
        out.println("</table>");
        out.println("<p>Total Sale: " + String.format("$%.2f", totalSale) + "</p>");
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
        String action = request.getParameter("action");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JavaMart PurchaseCart</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                switch (action) {
                    case "Purchase":
                        /* Note: we start a transaction here due to bug in GlassFish (GLASSFISH-21172)
                         * where the checked exception is lost unless a transaction is started before
                         * executing the transaction in the target CDI bean.
                         */
                        ut.begin();
                        shoppingCart.purchaseCart();
                        ut.commit();
                        out.println("<h1>Success! Your order will soon be shipped!</h1>");
                        break;
                    case "Cancel":
                        shoppingCart.resetCart();
                        out.println("<h1>Your order has been cancelled</h1>");
                        break;
                }
            } catch (PurchaseException ex) {
                logger.log(Level.INFO, ex.getMessage());
                out.println("<h2>" + ex.getMessage() + "</h2>");
            } catch (Exception ex) {
                out.println("<h2>Exception completing your transaction</h2>");
                logger.log(Level.INFO, ex.getMessage());
            }
            out.println("<form action =\"" + request.getContextPath() + "/JavaMart\"><input type=\"submit\" value=\"Return to Shopping\"></form>");
            out.println("</body>");
            out.println("</html>");
        }
        //processRequest(request, response);
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
