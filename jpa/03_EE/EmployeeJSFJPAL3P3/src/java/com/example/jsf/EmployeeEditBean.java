package com.example.jsf;

import com.example.dao.DAOException;
import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "editBean")
@SessionScoped
public class EmployeeEditBean implements Serializable {

    private Employee emp;
    @EJB
    private EmployeeDAO ejb;
    private boolean editReadonly = true;
    private boolean createSucceeded = false;

    public EmployeeEditBean() {
    }

    public Employee getEmp() {
        return emp;
    }

    public boolean isEditReadonly() {
        return editReadonly;
    }

    public boolean isCreateSucceeded() {
        return createSucceeded;
    }

    public String prepareCreate() {
        emp = new Employee();
        createSucceeded = false;
        return "create";
    }

    public void createEmployee() {
        try {
            ejb.add(emp);
            displayMessage("Successfully added new Employee id: " + emp.getId());
            createSucceeded = true;
        } catch (DAOException e) {
            reportError(e);
        }
    }

    public String prepareUpdate(int id) {
        String result = null;
        try {
            emp = ejb.findById(id);
            editReadonly = false;
            result = "edit";
        } catch (DAOException e) {
            reportError(e);
        }
        return result;
    }

    public void updateEmployee() {
        try {
            ejb.update(emp);
            displayMessage("Successfully update Employee id: " + emp.getId());
            editReadonly = true;
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