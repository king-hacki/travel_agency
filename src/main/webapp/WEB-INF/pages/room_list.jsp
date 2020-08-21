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
    </c:forEach>
</body>
</html>
