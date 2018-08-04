# Database Setup with NetBeans

## Setup the Database

To setup a database on NetBeans follow these steps.

1. Start NetBeans.
2. Open the Services tab.
3. Open the Databases section of the tab.
4. Right click JavaDB and select: Start Server.
5. Right click JavaDB and select: Create Database.

Fill in the information for the database and click `Ok`.

	Database name: LoginDb
	User name: oracle
	Password: oracle
	Confirm password: oracle

A an empty database named `LoginDb` is created. In addition, the interface will add a connection for the database. Double click the connection to connect to the database server.

## Setup the `USERS` Table

Now you need to populate your database with some test data. The `LoginShortTbl.sql` file provides the data to populate `USERS` table. To do this follow these steps.

1. If you have not done so, double click the LoginDb connection to connect NetBeans to the database server. 
2. Toggle the arrow control to reveal the data under the LoginDb connection.
3. Expand the information under the `oracle` user. 
4. Open the `LoginShortTbl.sql` file.
5. Create the database by clicking on the `Run SQL` icon (looks like a database).
6. Select LoginDb from the list of databases. Click `Ok`.

Your database is now created. If you open the Tables toggle, you should be able to view the table now.

## Test the Database with Servlet

Run the `DataExample.java` servlet to test the database from Java. This launches the GlassFish server and then your webserver.

Click on the DataExample link to see the results of a database query.

Look at the source code for this project. This servlet uses the DriverManager class to connect to and query the database. At this time, no JNDI has been used.

## Add a Connection Pool and a JNDI Resource

To add a connection pool for the new database, follow these steps.

1. Right click the project and select New --> Other.
2. Select GlassFish and JDBC Connection Pool. Click Next.
3. Enter the following information

	JDBC Connection Pool Name: LoginConnectionPool
	Extract from Existing Connection: <Select LoginDb>

4. Click Next.
5. On the Connection Pool Properies page take the defaults. Click Next.
6. Add a JDBC resource. Right click the project and select New --> Other --> Glassfish --> JDBC Resource.
	
	Use Existing JDBC Connection Pool: <Select LoginDb>
	JNDI Name: jdbc/login

7. Click finish to create a JDBC resource.
8. Run `DataExampleJndi.java`. A browser window should be launched with a link to the servlet. Click the link to test the Servlet.
9. Example the source code. Notice that in this case, a JNDI context is used to look a a name for the database. 





## Troubleshooting and Configuration Notes

#### Connection Pool Notes

When you setup a connection pool using NetBeans for your project. This sets up a connection pool in your project and also creates a pool on the server. If you need to reinstall the database and or application from scratch, you may need to delete the connection pool stored on the Glassfish server. This can be done from the GlassFish servers admin console, the JDBC section.

