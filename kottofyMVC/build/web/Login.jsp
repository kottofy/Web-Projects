<%--
    Document   : index
    Created on : Mar 22, 2011, 10:21:19 AM
    Author     : kottofy

    The entry page, with company logo and links to other views.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entry Page</title>
        <style type="text/css">
            BODY {
                background-color:#BEF781;
                text-align: center;
            }
            a
            {
                font-size:12pt;
                color:#FFFF00;
                font-family:Verdana;
                text-decoration:none
            }
            div
            {
                width: 150px;
                background-color:#04B4AE;
                font-size:14pt;
                color:#FFFFFF;
                font-family:Verdana;
                float:left;
                border:solid white;
                padding: 1em;

            }
            p{
                padding-top: 10em;
                padding-right: 10em;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div>
            <a href="index.jsp">Home</a><br>
        </div>
        <div class ="special">
            <form method="POST" action="Login">
                Username: <input type="text" name="username"> <br>
                Password:<input type="password" name="password"><br><br>
                <input type="submit" value="Login">
            </form>
        </div>
    </body>
</html>
