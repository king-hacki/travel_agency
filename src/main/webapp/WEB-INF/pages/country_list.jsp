<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Country List</title>
</head>
<body>
    <%@include file="../static/header.html"%>
    <c:if test="${error ne null}">
        <p style="color: red">${error}</p>
    </c:if>
    <h2>All Countries</h2>
    <c:forEach items="${allCountries}" var="country">
        <a href="/hotel/${country.id}">${country.name}</a>
    </c:forEach>
</body>
</html>
