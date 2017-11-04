package com.example.servlet;

import com.example.entity.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet(name = "CreateEmployee", urlPatterns = {"/CreateEmployee"})
public class CreateEmployee extends HttpServlet {

    @PersistenceUnit(unitName = "EmployeePU")
    private EntityManagerFactory emf;
    // Bean-managed transactions
    @Resource
    private UserTransaction utx;

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create a new Employee Record</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Enter New Employee information</h1>");
            out.println("<form action=\"" + request.getContextPath() + "/CreateEmployee\" method=\"post\">");
            out.println("First name: <input type=\"text\" name=\"firstname\" /><br />");
            out.println("Last name: <input type=\"text\" name=\"lastname\" /><br />");
            out.println("Birth date: <input type=\"date\" name=\"birthdate\"  />ex: June 15, 1970<br />");
            out.println("Salary: $<input type=\"number\" name=\"salary\" />ex: 101345.56<br />");
            out.println("<input type=\"submit\" value=\"Submit\" />");
            out.println("</form>");
            out.println("<p><a href=" + request.getContextPath() + ">Back</a>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        Date birthDate = null;
        float salary = 0.0f;
        Employee emp = null;

        // Try the conversion of the birth and salary strings
        try {
            SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
            String birthDateStr = request.getParameter("birthdate").trim();
            birthDate = new Date(df.parse(birthDateStr).getTime());
            String salaryStr = request.getParameter("salary").trim();
            salary = Float.parseFloat(salaryStr);
            emp = new Employee(firstName, lastName, birthDate, salary);
        } catch (NumberFormatException | ParseException pe) {
            request.setAttribute("errorMessage", "Exception parsing: " + pe);
            RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
            rd.forward(request, response);
        }

        // Solution code
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            utx.begin();
            em.persist(emp);
            utx.commit();

        } catch (SecurityException | IllegalStateException e) {
            request.setAttribute("errorMessage", "Exception: " + e);
            RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
            rd.forward(request, response);
        } catch (NotSupportedException ex) {
            Logger.getLogger(CreateEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(CreateEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(CreateEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(CreateEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(CreateEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Success</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Created New Employee</h1><br/>");
            out.println("ID: " + emp.getId() + "<br/>");
            out.println("First Name: " + emp.getFirstName() + "<br/>");
            out.println("Last Name: " + emp.getLastName() + "<br/>");
            out.println("Birth Date: " + emp.getBirthDate() + "<br/>");
            out.println("Salary: " + emp.getSalary() + "<br/>");
            out.println("<p><a href=" + request.getContextPath() + ">Back</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
            em.close();
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
