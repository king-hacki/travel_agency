<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful Book</title>
</head>
<body>
    <%@include file="../static/header.html"%>
    <h1>You just booked a room</h1>
    <h2>In Hotel: ${rentEntity.room.hotel.name}</h2>
    <h2>Room Number: ${rentEntity.room.number}</h2>
    <h2>On Date:</h2>
    <h3>Start: ${rentEntity.startRentDate}</h3>
    <h3>End: ${rentEntity.endRentDate}</h3>
</body>
</html>
