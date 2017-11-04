package com.example.ejb;

import com.example.dao.DAOException;
import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EmployeeEJBDAO implements EmployeeDAO {

    @PersistenceContext(unitName = "EmployeePU")
    private EntityManager em;

    public EmployeeEJBDAO() {
    }

    @Override
    public void add(Employee emp) throws DAOException {
        throw new DAOException("Add Employee not yet implemented");
    }

    @Override
    public void update(Employee emp) throws DAOException {
        throw new DAOException("Update Employee not yet implemented");
    }

    @Override
    public void delete(int id) throws DAOException {
        throw new DAOException("Delete Employee not yet implemented");
    }

    @Override
    public Employee findById(int id) throws DAOException {
        throw new DAOException("Find Employee not yet implemented");
    }

    @Override
    public Employee[] getAllEmployees() throws DAOException {
        Employee[] empArray = null;
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> emps = query.getResultList();
            empArray = emps.toArray(new Employee[0]);
        } catch (Exception e) {
            throw new DAOException("Exception thrown in getAllEmployees method: ", e.getCause());
        }
        return empArray;
    }
}