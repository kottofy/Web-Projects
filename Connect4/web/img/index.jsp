<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" import="connect4.GameBoard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean class="connect4.GameBoardBean" scope="session" id="bean"></jsp:useBean>
<jsp:setProperty property="column" name="bean"></jsp:setProperty>
<jsp:setProperty property="reset" name="bean"></jsp:setProperty>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kottofy's Connect 4!</title>
    </head>
    <body>
        <div>
            <h2> Kottofy's Connect 4!</h2>
            <br>
            <c:if test="${bean.winner != -1}">
                <h3> Game has been won by: </h3>
                <c:if test="${bean.winner == 2}">Red!!</c:if>
                <c:if test="${bean.winner == 3}">Black!!</c:if>
                
            </c:if>

            <h5>Current player: </h5><c:if test="${bean.currentPlayer == 2}">Red</c:if><c:if test="${bean.currentPlayer == 3}">Black</c:if>

            <br><br>
            <c:if test="${bean.gameStatus == 1}">Play on!</c:if>
            <c:if test="${bean.gameStatus != 1}">Please reset</c:if>

            <br><br><br>
            <form method="post" action="index.jsp">
                <table border="1">
                    <c:forEach var="row" begin="0" end="5">
                        <tr>
                            <c:forEach var="col" begin="0" end="6">
                                <td>
                                    <c:choose>
                                        <c:when test="${bean.board[row][col] == 1}"><img src="whitecell.GIF" width="33" height="36" alt="unoccupied"></c:when>
                                        <c:when test="${bean.board[row][col] == 2}"><img src="redChecker.jpg" width="33" height="36" alt="red"></c:when>
                                        <c:when test="${bean.board[row][col] == 3}"><img src="blackChecker.jpg" width="33" height="36" alt="black"></c:when>
                                    </c:choose>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    <c:if test="${bean.gameStatus == 1}">
                        <tr>
                        <td><input type="submit" value="0" name="column"></td>
                        <td><input type="submit" value="1" name="column"></td>
                        <td><input type="submit" value="2" name="column"></td>
                        <td><input type="submit" value="3" name="column"></td>
                        <td><input type="submit" value="4" name="column"></td>
                        <td><input type="submit" value="5" name="column"></td>
                        <td><input type="submit" value="6" name="column"></td>
                    </tr>
                    </c:if>
                </table>
            </form>
           
            <br><br>
            <form action="index.jsp" method="post">
                <p><input type="submit" value="Reset" name="reset"></p>
            </form>       
        </div>
    </body>
</html>