package com.example.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExampleServlet", urlPatterns = {"/ExampleServlet"})
public class ExampleServlet extends HttpServlet {
  
  private final Item item01 = new Item();
  private final Item item02 = new Item();
  private final Item item03 = new Item();
  
  @Inject @AddItem Event<Item> addEvent;
  @Inject @RemoveItem Event<Item> removeEvent;
  
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      // Init items
      item01.setItemData("Flash Drive 32GB", 10.00);
      item02.setItemData("Java programming book", 40.00);
      item03.setItemData("USB Mouse", 15.00);
      
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Cart Event Handler</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Cart Event Handler Example at " + request.getContextPath() + "</h1>");
      out.println("Performed these transactions: add, add, add, remove"); 
      addEvent.fire(item01);
      addEvent.fire(item02);
      addEvent.fire(item03);
      removeEvent.fire(item01);
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
