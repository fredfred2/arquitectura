package com.example.db;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "DataExampleJndi", urlPatterns = {"/DataExampleJndi"})
public class DataExampleJndi extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataExampleJndi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DataExampleJndi at " + request.getContextPath() + "</h1>");
            out.println(this.queryDatabase());
            out.println("</body>");
            out.println("</html>");
        }
    }

    public String queryDatabase() {
        String outStr = "";

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/login");
            Connection con = ds.getConnection();

            PreparedStatement stmt = con.prepareStatement("select * from users");
            ResultSet rs = stmt.executeQuery();
            int rowNum = 1;
            while (rs.next()) {
                outStr += "=== Row " + rowNum++ + " ===<br/>";
                outStr += "User Id : " + rs.getInt(1) + "<br/>";
                outStr += "Full name: " + rs.getString(2) + "<br/>";
                outStr += "Email: " + rs.getString(3) + "<br/>";
                outStr += "Password: " + rs.getString(4) + "<br/>";
                outStr += "Last Login Date: " + rs.getDate(5) + "<br/>";
                outStr += "<hr/>";
            }
        } catch (SQLException err) {
            return "SQL Exception: " + err.getMessage();
        } catch (Exception e) {
            return "Other Exception: " + e.getMessage();
        }
        return outStr;

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
