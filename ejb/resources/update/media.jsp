<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="/WebMediaManager/css/bootstrap.min.css" rel="stylesheet">
        <link href="/WebMediaManager/css/media-manager.css" rel="stylesheet">
        <title>Media Manager</title>
    </head>
    <body>
        <jsp:useBean id="itemBean" type="com.example.media.MediaItem" scope="request"/>
        <div class="container">
            <div class="page-header">
                <h1>Media Manager</h1>
            </div>
            <form method="post" action="/WebMediaManager/MediaController?action=details&itemId=${itemBean.id}">
            <div>
                <button type="button" class="btn-default">View</button>
                <input type="submit" class="btn-primary" value="Details" />
            </div>
            </form>
            <h4>${itemBean.title}</h4>
            <c:choose>
                <c:when test="${itemBean.type == 'IMAGE'}">
                    <img src="/WebMediaManager/download/${itemBean.id}" class="media-large-image"/>
                </c:when>  
                <c:when test="${itemBean.type == 'MP4_VIDEO' || itemBean.type == 'OGV_VIDEO'}">
                    <video class="media-large-video" controls>
                        <source src="/WebMediaManager/download/${itemBean.id}" >                
                    </video>
                </c:when>
                <c:otherwise>
                    <h2 style="text-align: center">This image type is not supported</h2>
                </c:otherwise>
            </c:choose>
            <nav class="navbar navbar-inverse">
                <div class="container">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/WebMediaManager">Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <script src="/WebMediaManager/js/jquery-1.11.1.min.js"></script>
        <script src="/WebMediaManager/js/bootstrap.min.js"></script>
    </body>
</html>
