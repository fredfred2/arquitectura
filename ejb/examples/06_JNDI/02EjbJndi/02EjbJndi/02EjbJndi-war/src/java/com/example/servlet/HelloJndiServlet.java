package com.example.servlet;

import com.example.ejb.Hello;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloJndiServlet", urlPatterns = {"/HelloJndiServlet"})
public class HelloJndiServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      Hello helloMsg = getContext();
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet HelloJndiServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet HelloJndiServlet at " + request.getContextPath() + "</h1>");
      out.println("<p>Message from bean: " + helloMsg.sayHello() + "</p>");
      out.println("</body>");
      out.println("</html>");
    }
  }
  
  public Hello getContext(){
    Hello helloMsg = null;
    try{
      Context ctx = new InitialContext();
      helloMsg = (Hello)ctx.lookup("java:global/02EjbJndi/02EjbJndi-ejb/HelloBean");
    }catch (NamingException e){
      System.out.println("Naming exception: " + e.getMessage());
    }catch (Exception e){
      System.out.println("Other exception: " + e.getMessage());
    }
    return helloMsg;
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
