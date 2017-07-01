<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Photo Uploader</title>
    </head>

    <body>
        <div class="titlebar">
            <h1>Error</h1>
            <a class="titlebar-back" href="${pageContext.request.contextPath}/">Back</a>
        </div>

        <h2>${errorMessage}</h2>

    </body>
</html>
