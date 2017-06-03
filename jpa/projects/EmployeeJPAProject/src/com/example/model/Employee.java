package com.example.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private float salary;

    public Employee(String firstName, String lastName, Date birthDate, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public float getSalary() {
        return salary;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID:   " + getId() + "\n"
                + "Employee Name: " + getFirstName() + " " + getLastName() + "\n"
                + "Birth Date:    " + new SimpleDateFormat("MMM d, yyyy").format(getBirthDate()) + "\n"
                + "Salary:        " + NumberFormat.getCurrencyInstance().format((double) getSalary());
    }
}
