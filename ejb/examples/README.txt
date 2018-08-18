==Running the examples==

=== SETUP ===


-  Ensure WebLogic Server is configured in NetBeans and running.

-  Open the example projects in NetBeans and remember to clean and build them.

-  For executing, example, of Lesson 6,DataExampleJndi.java you need to configure the DataSource jndi/loginDb in WebLogic server thru Admin console

 - Add the LoginDb database
   1. Open the Services tab
   2. Open the databases node
   3. right click the Java DB and select Start Server if available
   4. right click the Jaba DB and select create database
   5. Set the name of the database: "LoginDb", user name: "oracle" and password: <refer to the security credentials page>
   6. Click OK
   7. In the netbeans menu select window -> favorites
   8. Browse to /home/oracle/labs/resources
   9. Open the LoginShortTbl.sql file
   10. in the connection selector select the LoginDb connection.
   11. Click the Run SQL icon (on the right of the selector, a database with a green arrow)

 - Add the EmployeeDB database
   1. Open the Services tab
   2. Open the databases node
   3. right click the Java DB and select Start Server if available
   4. right click the Jaba DB and select create database
   5. Set the name of the database: "EmployeeDB", user name: "oracle" and password: <refer to the security credentials page>
   6. Click OK
   7. In the netbeans menu select window -> favorites
   8. Browse to /home/oracle/labs/examples/14_JPA
   9. Open the EmployeeDB.sql file
   10. in the connection selector select the EmployeeDB connection.
   11. Click the Run SQL icon (on the right of the selector, a database with a green arrow)

=== 

