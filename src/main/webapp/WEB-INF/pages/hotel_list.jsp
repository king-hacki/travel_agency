<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Hotels</title>
</head>
<body>
    <%@include file="../static/header.html"%>
    <h2>All Hotels in Country: ${country.name}</h2>
    <c:forEach items="${hotels}" var="hotel">
        <h4>${hotel.name}</h4>
    </c:forEach>
</body>
</html>
