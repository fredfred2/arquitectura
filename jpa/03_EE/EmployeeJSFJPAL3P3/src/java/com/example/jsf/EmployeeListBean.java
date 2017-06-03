package com.example.jsf;

import com.example.dao.DAOException;
import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "listBean")
@RequestScoped
public class EmployeeListBean implements Serializable {

    private String busqueda;
    private boolean flag = false;
    private Employee[] allEmployees;

    public String findByLastName() {
        flag = true;
        allEmployees = ejb.findByLastname(busqueda);
        return "index";
    }

    public Employee[] getAllEmployees() {
        if (flag) {
            return allEmployees;
        } else {
            try {
                return ejb.getAllEmployees();
            } catch (DAOException e) {
                reportError(e);
            }
        }
        return null;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    private Employee emp;
    @EJB
    private EmployeeDAO ejb;
    private boolean deleteSucceeded = false;

    public EmployeeListBean() {
    }

    public Employee getEmp() {
        return emp;
    }

    public String viewEmployee(int id) {
        String result = null;
        try {
            emp = ejb.findById(id);
            result = "view";
        } catch (DAOException e) {
            reportError(e);
        }
        return result;
    }

    public boolean isDeleteSucceeded() {
        return deleteSucceeded;
    }

    public void deleteEmployee(int id) {
        try {
            emp = ejb.findById(id);
            ejb.delete(emp.getId());
            displayMessage("Successfully deleted Employee with id: " + emp.getId());
            deleteSucceeded = true;
        } catch (DAOException e) {
            reportError(e);
        }
    }

    private void displayMessage(String msg) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        ctx.addMessage(null, message);
    }

    private void reportError(Exception e) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
        ctx.addMessage(null, message);
    }
}
