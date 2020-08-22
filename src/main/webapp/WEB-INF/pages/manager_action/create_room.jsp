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
        <table>
            <td><form:label path="number">Number</form:label></td>
            <td><form:input path="number"/></td>
            <td><form:select path="level">
                <form:option value="ECONOMY"/>
                <form:option value="STANDARD"/>
                <form:option value="LUX"/>
                <form:option value="SUPERLUX"/>
            </form:select></td>
            <td><input type="submit" value="Create Room"></td>
        </table>
    </form:form>
</body>
</html>
