<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>User Rents</title>
</head>
<body>
    <%@include file="../../static/header.html"%>
    <h2>User Rents</h2>
    <h4>User first name: ${user.firstName}</h4>
    <h4>User last name: ${user.lastName}</h4>
    <h4>User email: ${user.email}</h4>
    <h3>All Rents</h3>
    <c:forEach items="${userRents}" var="rent">
        <table>
            <tr>
                <td>Rent Start: ${rent.startRentDate} | </td>
                <td>Rent End: ${rent.endRentDate} |</td>
            </tr>
        </table>
    </c:forEach>
</body>
</html>
