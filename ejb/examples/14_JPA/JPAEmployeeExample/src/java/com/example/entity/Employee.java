package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Employee implements Serializable {

    @Id
    @Min(value=1, message="The ID must be greater than 0")
    private int id;
    private String firstName;
    @NotNull
    @Size(min = 1, message="Last name cannot be empty")
    private String lastName;
    @Past(message = "The birth Date must be in the past!")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private float salary;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, Date birthDate, float salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}