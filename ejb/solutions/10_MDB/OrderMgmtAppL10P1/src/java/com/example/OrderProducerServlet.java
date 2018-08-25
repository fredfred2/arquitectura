package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderProducerServlet"
        + "Servlet", urlPatterns = {"/OrderProducerServlet"})
public class OrderProducerServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger("com.example.OrderProducerServlet");

    @Inject
    private OrderProducerBean sender;
    @Inject
    private OrderReceiverBean receiver;

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
        String itemId = request.getParameter("itemId");
        String customerId = request.getParameter("customerId");
        String orderId = request.getParameter("orderId");
        String qty = request.getParameter("quantity");

        logger.log(Level.INFO, "Start Sending Order Request...");

        try (PrintWriter out = response.getWriter()) {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>JMS2 Order Management System</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3> Send/Receive Message using JMS2</h3>");
            String order = ("<OrderId = " + orderId + " ItemId = " + itemId + " CustomerId = " + customerId
                    + " Quantity = " + qty + ">");
            sender.sendMessage(order);
            logger.log(Level.INFO, "order sent: " + order);
            out.format("Message sent to orderQueue");
            //receiver.receiveMessage();
            
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
