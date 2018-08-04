<%-- 
    Document   : index
    Created on : Oct 6, 2014, 11:18:44 AM
    Author     : mheimer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <table>
        <c:forEach var="item" items="${{'orange','apple','pear'}}">
            <tr><td>${item}</td></tr>
        </c:forEach>
        </table>
    </body>
</html>
