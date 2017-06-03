package com.example.test;

import com.example.dao.DAOException;
import com.example.dao.EmployeeDAOImpl;
import com.example.model.Employee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeTestInteractive {

    public static void main(String[] args) throws IOException {
        Employee emp;
        Employee[] allEmps;
        String action;
        int id;

        EmployeeDAOImpl dao = new EmployeeDAOImpl();

        // try-with-resources will call close on the buffered reader and EmployeeDAOImpl objects
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("\n\n[C]reate | [R]ead | [U]pdate | [D]elete | [L]ist | [Q]uit: ");
                action = in.readLine();
                if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
                    break;
                }

                switch (action.toUpperCase().charAt(0)) {
                    // Create a new employee record
                    case 'C':
                        try {
                            emp = inputEmployee(in);
                            dao.add(emp);
                            System.out.println("Successfully added Employee Record: " + emp.getId());
                            System.out.println("\n\nCreated " + emp);
                        } catch (NumberFormatException | ParseException | DAOException e) {
                            System.out.println("Exception adding Employee: " + e);
                        }
                        break;

                    // Display an employee record
                    case 'R':
                        System.out.println("Enter int value for employee id: ");
                        try {
                            id = new Integer(in.readLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Employee id: " + e);
                            break;
                        }

                        // Find this Employee record
                        try {
                            emp = dao.findById(id);
                            if (emp != null) {
                                System.out.println(emp + "\n");
                            } else {
                                System.out.println("\n\nEmployee " + id + " not found");
                                break;
                            }
                        } catch (DAOException e) {
                            System.out.println("Exception displaying Employee with id: " + id + " :" + e);
                        }

                        break;

                    // Update an existing employee record    
                    case 'U':
                        System.out.println("Enter int value for employee id: ");
                        try {
                            id = new Integer(in.readLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer for id " + e);
                            break;
                        }
                        // Find this Employee record
                        emp = null;
                        try {
                            emp = dao.findById(id);
                            if (emp == null) {
                                System.out.println("\n\nEmployee " + id + " not found");
                                break;
                            }
                        } catch (DAOException e) {
                            System.out.println("Exception finding Employee with id: " + id + " :" + e);
                        }
                        // Go through the record to allow changes
                        try {
                            emp = inputEmployee(in, emp);
                            dao.update(emp);
                            System.out.println("Successfully updated Employee Record: " + emp.getId());
                        } catch (NumberFormatException | ParseException | DAOException e) {
                            System.out.println("Exception updating Employee with id: " + id + " :" + e);
                            break;
                        }
                        break;

                    // Delete an employee record
                    case 'D':
                        System.out.println("Enter int value for employee id: ");
                        try {
                            id = new Integer(in.readLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Employee id: " + e);
                            break;
                        }

                        // Find this Employee record
                        try {
                            dao.delete(id);
                            System.out.println("Deleted Employee " + id);
                        } catch (DAOException e) {
                            System.out.println("Exception deleting Employee with id: " + id + " :" + e);
                        }

                        break;

                    // Display a list (Read the records) of Employees
                    case 'L':
                        try {
                            allEmps = dao.getAllEmployees();
                            for (Employee employee : allEmps) {
                                System.out.println(employee + "\n");
                            }
                        } catch (DAOException e) {
                            System.out.println("Exception getting list: " + e);
                        }
                        break;

                    default:
                        continue;
                }
            } catch (IOException io) {
                System.out.println("Error reading input, quiting.");
                break;
            }
        }
    }

    public static Employee inputEmployee(BufferedReader in) throws ParseException, IOException {
        return inputEmployee(in, null, true);
    }

    public static Employee inputEmployee(BufferedReader in, Employee empDefaults) throws ParseException, IOException {
        return inputEmployee(in, empDefaults, false);
    }

    public static Employee inputEmployee(BufferedReader in, Employee empDefaults, boolean newEmployee) throws ParseException, IOException {
        SimpleDateFormat df = new SimpleDateFormat("MMM d, yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        int id;
        String firstName;
        String lastName;
        Date birthDate;
        Employee emp;
        float salary;

        if (newEmployee) {
            System.out.println("Enter int value for employee id: ");
            try {
                id = new Integer(in.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            id = empDefaults.getId();
            System.out.println("Modify the fields of Employee record: " + id
                    + ". Press return to accept current value.");
        }

        String prompt = "Enter value for employee first name" + ((empDefaults == null) ? "" : " [" + empDefaults.getFirstName() + "]");
        System.out.println(prompt + " : ");
        firstName = in.readLine().trim();
        if (firstName.equals("") && empDefaults != null) {
            firstName = empDefaults.getFirstName();
        }
        if (firstName.length() < 1) {
            System.out.println("Please retry with a valid first name");
            throw new ParseException("Invalid first name", 0);
        }


        prompt = "Enter value for employee last name" + ((empDefaults == null) ? "" : " [" + empDefaults.getLastName() + "]");
        System.out.println(prompt + " : ");
        lastName = in.readLine().trim();
        if (lastName.equals("") && empDefaults != null) {
            lastName = empDefaults.getLastName();
        }
        if (lastName.length() < 1) {
            System.out.println("Please retry with a valid last name");
            throw new ParseException("Invalid last name", 0);
        }


        prompt = "Enter value for employee birth date (" + df.toLocalizedPattern() + ")"
                + ((empDefaults == null) ? "" : " [" + df.format(empDefaults.getBirthDate()) + "]");
        System.out.println(prompt + " : ");
        String dateStr = in.readLine().trim();
        if (dateStr.equals("") && empDefaults != null) {
            birthDate = empDefaults.getBirthDate();
        } else {
            birthDate = null;
            try {
                birthDate = new Date(df.parse(dateStr).getTime());
            } catch (ParseException e) {
                System.out.println("Please retry with a valid birth date: " + e.getMessage());
                throw e;
            }
        }


        prompt = "Enter float value for employee salary"
                + ((empDefaults == null) ? "" : " [" + nf.format((double) empDefaults.getSalary()) + "]");
        System.out.println(prompt + " : ");
        salary = 0;
        try {
            String amt = in.readLine().trim();
            if (!amt.equals("")) {
                salary = Float.parseFloat(amt);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please retry with a valid float salary: " + e.getMessage());
            throw e;
        }
        if (salary == 0 && empDefaults != null) {
            salary = empDefaults.getSalary();
        }

        // Create an Employee object
        emp = new Employee(id, firstName, lastName, birthDate, salary);
        return emp;
    }
}