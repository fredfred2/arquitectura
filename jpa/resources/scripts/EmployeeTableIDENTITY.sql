# JavaDB Database setup
# JDBC URL: jdbc:derby://localhost:1527/EmployeeDB
# User: oracle
# Password: welcome1
DROP TABLE EMPLOYEE;

CREATE TABLE EMPLOYEE (
   	ID INTEGER GENERATED ALWAYS AS IDENTITY, 
   	FIRSTNAME VARCHAR(40) NOT NULL, 
	LASTNAME VARCHAR(40) NOT NULL,
	BIRTHDATE DATE,
	SALARY REAL,
	PRIMARY KEY (ID)
);

# Note the table generates the primary key
# 

INSERT INTO EMPLOYEE (FirstName, LastName, BirthDate, Salary) VALUES ('Troy', 'Hammer', '1965-03-31', 102109.15);
INSERT INTO EMPLOYEE (FirstName, LastName, BirthDate, Salary) VALUES ('Michael', 'Walton', '1986-08-25', 93400.20);
INSERT INTO EMPLOYEE (FirstName, LastName, BirthDate, Salary) VALUES ('Thomas', 'Fitzpatrick', '1961-09-22', 75123.45);
INSERT INTO EMPLOYEE (FirstName, LastName, BirthDate, Salary) VALUES ('Abhijit', 'Gopali', '1956-06-01', 89345.00);
INSERT INTO EMPLOYEE (FirstName, LastName, BirthDate, Salary) VALUES ('Rajiv', 'Sudahari', '1969-12-22', 68400.22);
INSERT INTO EMPLOYEE (FirstName, LastName, BirthDate, Salary) VALUES ('Patrice', 'Bergeron', '1970-09-18', 109345.00);