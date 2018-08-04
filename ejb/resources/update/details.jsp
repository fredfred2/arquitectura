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
            <form method="post" action="/WebMediaManager/MediaController?action=item&itemId=${itemBean.id}">
                <div>
                    <input type="submit" class="btn-primary" value="View" />
                    <button type="button" class="btn-default">Details</button>
                </div>
            </form>
            <form method=post action="/WebMediaManager/MediaController?action=newDetails&itemId=${itemBean.id}">
                <table class="table table-bordered">
                    <tr>
                        <td>ID:</td>
                        <td>${itemBean.id}</td>
                    </tr>
                    <tr>
                        <td>Type:</td>
                        <td>${itemBean.type}</td>
                    </tr>
                    <tr>
                        <td>Title:</td>
                        <td><input type="text" name="title" class="form-control" placeholder="${itemBean.title}"></td>
                    </tr>
                    <tr>
                        <td>Date:</td>
                        <td><input type="date" name="date" class="form-control" placeholder="${itemBean.simpleDate}"></td>
                    </tr>
                </table>
                <button type="submit" class="btn-primary">Submit Changes</button>
            </form>

            <h3><a href="/WebMediaManager">Home</a></h3>
        </div>
    </body>
</html>
