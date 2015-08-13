<%--
    Document   : index
    Created on : Mar 22, 2011, 10:21:19 AM
    Author     : kottofy

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
        <h3> Congrats! You just purchased: </h3>
        <div>
            <a href="index.jsp">Home</a><br>
            <a href="Shop.jsp">Continue Shopping</a><br>
            <a href="Edit.jsp">Edit Account</a><br>
            <a href="MyOrders.jsp">View Your Orders</a><br>
        </div>
        <table>
            <c:forEach items="${order}" var="order">
                <tr>
                    <td>
                        <c:out value="${order.quantity}"></c:out>
                    </td>
                    <td>
                        <c:out value="${order.sku}"></c:out>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
