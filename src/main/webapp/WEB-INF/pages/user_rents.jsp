<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>User's Rents</title>
</head>
<body>
    <%@include file="../static/header.html"%>
    <h2>All Rents of User: ${user} </h2>
    <c:forEach items="${userRents}" var="rents">
        <h4>${rents}</h4>
    </c:forEach>
</body>
</html>
