<%--
    Document   : Edit
    Created on : Mar 22, 2011, 10:27:45 AM
    Author     : kottofy

    used to either enter the data for a new customer or edit the data for an
    existing customer.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
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
            .special
            {
                width: 350px;
                background-color: #990099;
                text-align: right;
            }
            p
            {
                padding-top: 10em;
                padding-right: 10em;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h3>Create a new account or Edit existing Account</h3>
        <div>
            <a href="index.jsp">Home</a><br>
            <a href="Login.jsp">Login</a><br>
            <a href="Checkout.jsp">CheckOut</a><br>
            <a href="Shop.jsp">Shop</a><br>
            <a href="Edit.jsp">Edit Account</a><br>
            <a href="MyOrders.jsp">View Your Orders</a><br>
        </div>
        <div class="special">
            <form method="POST" action="Edit">
                Username: <input type="text" name="username"><br>
                Password: <input type="password" name="password"><br>
                First Name: <input type="text" name="firstName"><br>
                Last Name: <input type="text" name="lastName"><br>
                Address: <input type="text" name="address"><br>
                City: <input type="text" name="city"><br>
                State: <select name="state">
                    <option selected="selected">GA</option>
                    <option>AL</option><option>AK</option><option>AZ</option>
                    <option>AR</option><option>CA</option><option>CO</option>
                    <option>CT</option><option>DE</option><option>DC</option>
                    <option>FL</option><option>HI</option>
                    <option>ID</option><option>IL</option><option>IN</option>
                    <option>IA</option><option>KS</option><option>KY</option>
                    <option>LA</option><option>ME</option><option>MD</option>
                    <option>MA</option><option>MI</option><option>MN</option>
                    <option>MS</option><option>MO</option><option>MT</option>
                    <option>NE</option><option>NV</option><option>NH</option>
                    <option>NJ</option><option>NM</option><option>NY</option>
                    <option>NC</option><option>ND</option><option>OH</option>
                    <option>OK</option><option>OR</option><option>PA</option>
                    <option>RI</option><option>SC</option><option>SD</option>
                    <option>TN</option><option>TX</option><option>UT</option>
                    <option>VT</option><option>VA</option><option>WA</option>
                    <option>WV</option><option>WI</option><option>WY</option>
                </select><br>
                Zip Code: <input type="text" name="zip"><br>
                Credit Card: <input type="text" name="creditCard"><br><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>