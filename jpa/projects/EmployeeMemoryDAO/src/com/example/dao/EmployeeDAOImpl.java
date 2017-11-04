package com.example.dao;

import com.example.model.Employee;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeDAOImpl implements EmployeeDAO {

    // thread-safe
    private Map<Integer, Employee> employeeData = new ConcurrentHashMap<>();
    
    @Override
    public void add(Employee emp) throws DAOException {
        if (!employeeData.containsKey(emp.getId())) {
            employeeData.put(emp.getId(), emp);
        } else {
            throw new DAOException("Employee id " + emp.getId() + " exists.");
        }
    }

    @Override
    public void update(Employee emp) throws DAOException {
        employeeData.put(emp.getId(), emp);
    }

    @Override
    public void delete(int id) throws DAOException {
        employeeData.remove(id);
    }

    @Override
    public Employee findById(int id) throws DAOException {
        return employeeData.get(id);
    }

    @Override
    public Employee[] getAllEmployees() throws DAOException {
        Collection<Employee> values = employeeData.values();
        return values.toArray(new Employee[0]);
    }
}