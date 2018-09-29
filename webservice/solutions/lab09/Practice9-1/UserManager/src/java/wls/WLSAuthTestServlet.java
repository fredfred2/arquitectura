/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mheimer
 */
@WebServlet(name = "WLSAuthTestServlet", urlPatterns = {"/WLSAuthTestServlet"})
public class WLSAuthTestServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass().getPackage().getName());
    @EJB
    private WLSJMXManagementSessionBean bean;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /* TODO output your page here. You may use following sample code. */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>WLS Authentication Management Demo</title>");
        out.println("</head>");
        out.println("<body>");
        try {
            try {
                out.println("<h2>Users</h2>");
                List<String> users = bean.getUserList();
                for (String userName : users) {
                    out.print("User: " + userName + " - Groups: ");
                    List<String> groups = bean.getMemberGroups(userName);
                    for (String group : groups) {
                        out.println(group + " ");
                    }
                    out.println("<br/>");
                }
                out.println("<h2>Groups</h2>");
                List<String> groups = bean.getGroupList();
                for (String groupName : groups) {
                    out.print("Group: " + groupName + " - Users: ");
                    List<String> groupMembers = bean.getGroupMembers(groupName);
                    for (String user : groupMembers) {
                        out.println(user + " ");
                    }
                    out.println("<br/>");
                }
            } catch (Exception ex) {
                out.println("Failed to list users and groups: " + ex.getMessage());
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
