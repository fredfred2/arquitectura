/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import cards.CardDeckSessionBean;
import cards.CardDeckSessionBeanService;
import cards.CardType;
import cards.ColorType;
import cards.StackType;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import util.CardUtils;

/**
 *
 * @author mheimer
 */
@WebServlet(name = "WSClient", urlPatterns = {"/WSClient"})
public class WSClient extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_7001/CardDeckSessionBean/CardDeckSessionBeanService.wsdl")
    private CardDeckSessionBeanService service;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StackType deck;
        synchronized (this) {
            CardDeckSessionBean port = service.getCardDeckSessionBeanPort();
            int deckId1 = port.createDeck(2);
            port.shuffleDeck(deckId1);
            deck = port.getDeck(deckId1);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WSClientServlet</title>");
            out.println("<link rel='stylesheet' type='text/css' href='card.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WSClient at " + request.getContextPath() + "</h1>");
            for (CardType card : deck.getCard()) {
                if (CardUtils.getColor(card) == ColorType.BLACK) {
                    out.println("<span class='card'>" + CardUtils.toUnicode(card) + "</span>");
                } else {
                    out.println("<span class='redcard'>" + CardUtils.toUnicode(card) + "</span>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
