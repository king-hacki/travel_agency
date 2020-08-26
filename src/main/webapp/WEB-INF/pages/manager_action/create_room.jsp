<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Create Room</title>
</head>
<body>
    <%@include file="../../static/header.html"%>
    <h2>Create new Room</h2>
    <form:form action="/user_manager/create_room/${hotelId}" method="post" modelAttribute="room">
        <p><form:label path="number">Number</form:label></p>
        <p><form:errors path="number" cssStyle="color: darkred"/></p>
        <p><form:input path="number"/></p>
        <p><form:select path="level">
            <form:option value="ECONOMY"/>
            <form:option value="STANDARD"/>
            <form:option value="LUX"/>
            <form:option value="SUPERLUX"/>
        </form:select></p>
        <p><input type="submit" value="Create Room"></p>
    </form:form>
</body>
</html>
