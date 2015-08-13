<%-- 
    Document   : index
    Created on : Feb 11, 2011, 4:24:21 PM
    Author     : kottofy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chart Generator</title>
    </head>
    <body>
        <h1>Enter Chart Data</h1>
        <form method="post" action="generateChart">
            <table border="1">
                <tr>
                    <td BGCOLOR="#20B2AA"><h3>Enter X-axis Label</h3></td>
                    <td BGCOLOR="#20B2AA"><h3>Enter Y-axis Label</h3></td>
                <tr>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="titleOne"></td>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="titleTwo"></td>
                </tr>
            </table>
            <br>
            <br>
            <table border="1" >
                <tr>
                    <td BGCOLOR="#20B2AA"><h3>Enter labels</h3></td>
                    <td BGCOLOR="#20B2AA"><h3>Enter data</h3></td>
                </tr>
                <tr>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="labelOne"></td>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="dataOne"></td>
                </tr>
                <tr>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="labelTwo"></td>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="dataTwo"></td>
                </tr>
                <tr>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="labelThree"></td>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="dataThree"></td>
                </tr>
                                <tr>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="labelFour"></td>
                    <td BGCOLOR="#20B2AA"><input type="text" size="20" name="dataFour"></td>
                </tr>
            </table>
        <br>
            <table>
                <tr>
                    <th>Type: </th>
                    <td><p><input type="radio" name="radios" value="pie"> Pie</p></td>
                    <td><p><input type="radio" name="radios" value="line"> Line</p></td>
                    <td><p><input type="radio" name="radios" value="bar"> Bar</p></td>
                </tr>
            </table>
            <input type="submit" value="Generate Chart" >
            <br><br><br><br>
            <p>Disclaimer: If an attribute is empty, it will default to 0.</p>
        </form>
    </body>
</html>
