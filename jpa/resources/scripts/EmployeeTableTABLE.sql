# JavaDB Database setup
# JDBC URL: jdbc:derby://localhost:1527/EmployeeDB
# User: oracle
# Password: welcome1
DROP TABLE EMPLOYEE;
DROP TABLE EMP_ID_GEN;

CREATE TABLE EMPLOYEE (
   	ID INTEGER, 
   	FIRSTNAME VARCHAR(40) NOT NULL, 
	LASTNAME VARCHAR(40) NOT NULL,
	BIRTHDATE DATE,
	SALARY REAL,
	PRIMARY KEY (ID)
);

CREATE TABLE EMP_ID_GEN (
        GEN_NAME VARCHAR(10),
        GEN_VAL INTEGER,
        PRIMARY KEY (GEN_NAME)
);


# Insert a row then increment the Emp_id
#

INSERT INTO EMPLOYEE VALUES (1, 'Troy', 'Hammer', '1965-03-31', 102109.15);
INSERT INTO EMPLOYEE VALUES (2, 'Michael', 'Walton', '1986-08-25', 93400.20);
INSERT INTO EMPLOYEE VALUES (3, 'Thomas', 'Fitzpatrick', '1961-09-22', 75123.45);
INSERT INTO EMPLOYEE VALUES (4, 'Abhijit', 'Gopali', '1956-06-01', 89345.00);
INSERT INTO EMPLOYEE VALUES (5, 'Rajiv', 'Sudahari', '1969-12-22', 68400.22);
INSERT INTO EMPLOYEE VALUES (6, 'Patrice', 'Bergeron', '1970-09-18', 109345.00);

# The emp_id_gen table stores the last value handed out
#

INSERT INTO EMP_ID_GEN VALUES ('EMP_ID', 6);