<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management</title>
    </head>
    <body>
        <h1>Employee Database</h1>
        <h3>Choose your operation:</h3>
        <a href="${pageContext.request.contextPath}/ListEmployees">List Current Employees</a><br/>
        <a href="${pageContext.request.contextPath}/CreateEmployee">Create a new Employee record</a><br/>
        Find an Employee record<br/>
        Update an existing Employee record<br/>
        Delete an Employee record<br/>
    </body>
</html>
