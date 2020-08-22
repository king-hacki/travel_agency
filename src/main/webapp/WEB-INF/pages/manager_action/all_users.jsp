<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <%@include file="../../static/header.html"%>
    <h2>All Users Table </h2>
    <c:forEach items="${allUsers}" var="user">
        <table>
            <tr>
                <td>${user.id} | </td>
                <td>${user.firstName} |</td>
                <td>${user.lastName} |</td>
                <td>${user.email} |</td>
                <td>${user.password} |</td>
                <td><a href=""></a></td>
            </tr>
        </table>
    </c:forEach>
</body>
</html>
