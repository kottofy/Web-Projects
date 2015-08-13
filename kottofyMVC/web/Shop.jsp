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
        <h3>Shop!</h3>
        <div>
            <a href="index.jsp">Home</a><br>
            <a href="CheckOut.jsp">CheckOut</a><br>
            <a href="Edit.jsp">Edit Account</a><br>
            <a href="MyOrders.jsp">View Your Orders</a><br>
            <a href="Login.jsp">Logout</a>
        </div>
        <table>
            <form action="Shop" method="POST">
                <tr>
                    <th colspan="5" >
                        Search: <input type="text" name="search" >
                        <input type="submit" value="Search">
                    </th>
                </tr>
                <tr>
                    <th>
                        Sku
                    </th>
                    <th>
                        Description
                    </th>
                    <th>
                        Price
                    </th>
                    <th colspan="2">
                        Quantity
                    </th>
                </tr>
                <tr>
                    <c:forEach items="${items}" var="item">
                    <tr>
                        <td>
                            <c:out value="${item.sku}"></c:out>
                        </td>
                        <td>
                            <c:out value="${item.description}"></c:out>
                        <td>
                            <c:out value="${item.price}"></c:out>
                        </td>
                        <td>
                            <input type="text" name="quantity" size="2">
                        </td>
                        <td>
                            <input type="submit" value="Add to cart" name="${item.sku}">
                        </td>
                    </tr>
                </c:forEach>
                </tr>
            </form>
            <form action="Shop" method="GET" >
                <td colspan="5">
                    <input type="submit" value="Check Out">
                </td>
            </form>
        </table>
    </body>
</html>
