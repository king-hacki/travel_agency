<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Hotels</title>
</head>
<body>
    <%@include file="../static/header.html"%>
    <h2>Create New Hotel</h2>
    <form action="/user_manager/create_hotel" method="get">
        <input type="hidden" name="countryId" id="countryId" value="${country.id}">
        <input type="hidden" name="name" id="name" value="${country.name}">
        <input type="submit" value="Create hotel in country ${country.name}">
    </form>
    <c:choose>
        <c:when test="${error_messag == null}">
            pizza
        </c:when>
        <c:otherwise>
            burger
        </c:otherwise>
    </c:choose>
    <h2>All Hotels in Country: ${country.name}</h2>
    <c:forEach items="${hotels}" var="hotel">
        <h4>Name: ${hotel.name}</h4>
        <form action="/room/all/${hotel.id}">
            <input type="submit" value="All Rooms in hotel">
        </form>
        <form action="/room/${hotel.id}" method="post">
            <table>
                <tr>
                    <td><label for="start">Start: </label></td>
                    <td><input type="date" name="start" id="start"></td>
                </tr>
                <tr>
                    <td><label for="end">End: </label></td>
                    <td><input type="date" name="end" id="end"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Find" />
                        <input type="reset" value="Clear" />
                    </td>
                </tr>
            </table>
        </form>
    </c:forEach>
</body>
</html>
