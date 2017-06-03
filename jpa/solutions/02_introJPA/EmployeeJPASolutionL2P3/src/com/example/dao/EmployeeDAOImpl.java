package com.example.dao;

import com.example.model.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public EmployeeDAOImpl() {
        emf = Persistence.createEntityManagerFactory("EmployeePU");
    }

    @Override
    public void add(Employee emp) throws DAOException {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Exception thrown in add method: ", e.getCause());
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Employee emp) throws DAOException {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Exception thrown in update method: ", e.getCause());
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        Employee emp = null;
        em = emf.createEntityManager();
        emp = em.find(Employee.class, id);
        if (emp == null) {
            throw new DAOException("Employee id: " + id + " does not exist to delete.");
        }
        try {
            em.getTransaction().begin();
            em.remove(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DAOException("Exception thrown in delete method: ", e.getCause());
        } finally {
            em.close();
        }
    }

    @Override
    public Employee findById(int id) throws DAOException {
        Employee emp;
        em = emf.createEntityManager();
        try {
            emp = em.find(Employee.class, id);
        } catch (Exception e) {
            throw new DAOException("Exception thrown in findById method: ", e.getCause());
        } finally {
            em.close();
        }
        return emp;
    }

    @Override
    public Employee[] getAllEmployees() throws DAOException {
        Employee[] empArray;
        em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> emps = query.getResultList();
            empArray = emps.toArray(new Employee[0]);
        } catch (Exception e) {
            throw new DAOException("Exception thrown in getAllEmployees method: ", e.getCause());
        } finally {
            em.close();
        }
        return empArray;
    }
}
