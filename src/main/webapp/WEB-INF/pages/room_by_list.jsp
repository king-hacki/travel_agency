<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Rooms</title>
</head>
<body>
    <%@include file="../static/header.html"%>
    <form action="/user_manager/create_room" method="get">
        <input type="hidden" name="hotelId" id="hotelId" value="${hotelId}">
        <input type="submit" value="Create new room">
    </form>
    <h2>Rooms in Hotel ${hotelName}</h2>
    <c:forEach items="${rooms}" var="room">
        <table>
            <tr>
                <td>Room number: ${room.number}</td>
                <td>Room level: ${room.level}</td>
            </tr>
        </table>
    </c:forEach>
    <form action="/country/list" method="get">
        <input type="submit" value="Let's book some rooms">
    </form>
</body>
</html>
