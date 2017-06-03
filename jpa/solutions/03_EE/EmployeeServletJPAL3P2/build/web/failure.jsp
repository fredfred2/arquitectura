<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Employee</title>
    </head>

    <body>
        <div class="titlebar">
            <h1>Error</h1>
            <a href="${pageContext.request.contextPath}/">Back</a>
        </div>

        <h2>${errorMessage}</h2>

    </body>
</html>
