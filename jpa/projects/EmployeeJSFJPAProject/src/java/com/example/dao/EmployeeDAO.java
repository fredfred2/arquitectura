package com.example.dao;

import com.example.entity.Employee;

public interface EmployeeDAO {

    public void add(Employee emp) throws DAOException;

    public void update(Employee emp) throws DAOException;

    public void delete(int id) throws DAOException;

    public Employee findById(int id) throws DAOException;

    public Employee[] getAllEmployees() throws DAOException;
}