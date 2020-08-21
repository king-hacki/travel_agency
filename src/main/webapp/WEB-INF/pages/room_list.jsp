<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Available Rooms</title>
</head>
<body>
    <%@include file="../static/header.html"%>
    <h2>Available Rooms in Hotel: ${hotel.name}</h2>
    <h2>Start Date: ${start}</h2>
    <h2>End Date: ${end}</h2>
    <c:forEach items="${availableRooms}" var="room">
        <p>--------------</p>
        <p>Room number: ${room.number}</p>
        <p>Room level: ${room.level}</p>
        <form action="/rent/book" method="post">
            <input type="hidden" name="room_id" id="room_id" value="${room.id}">
            <input type="hidden" name="start" id="start" value="${start}">
            <input type="hidden" name="end" id="end" value="${end}">
            <input type="submit" value="Book">
        </form>
    </c:forEach>
</body>
</html>
