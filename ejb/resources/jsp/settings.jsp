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
        <jsp:useBean id="qualifierBean" type="com.example.media.MediaQualifier" scope="session" />
        <div class="container">
            <div class="page-header">
                <h1>Media Manager <small class="hidden-xs">- Settings</small></h1>
            </div>
            <form method="post" action="/WebMediaManager/MediaController?action=newSettings" class="settings-form">
                <input type="hidden">
                <label>Type: </label>
                <table>
                    <tr>
                        <td>
                            <input type="radio" name="type" value="images"/><label> Images Only</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" name="type" value="videos"/><label> Videos Only</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" name="type" value="both"  checked="true"/><label> Images+Videos</label>
                        </td>
                    </tr>
                </table>
                <br/><label>Sorting Order: </label>
                <select name="sortOrder" size="1" class="btn-primary">
                    <option value="DATE_ASC">DATE_ASC</option>
                    <option value="DATE_DESC">DATE_DESC</option>
                    <option value="TITLE_ASC" selected>TITLE_ASC</option>
                    <option value="TITLE_DESC">TITLE_DESC</option>
                </select>
                <p/>
                <input type="submit" value="Submit" class="btn-primary"/>  
            </form>
            <nav class="navbar navbar-inverse">
                <div class="container">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/WebMediaManager">Home</a></li>
                    </ul>
                </div>
            </nav>
        </div>
    </body>
</html>
