<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Create Country</title>
</head>
<body>
    <%@include file="../../static/header.html"%>
    <c:if test="${error ne null}">
        <p style="color: red">${error}</p>
    </c:if>
    <h2>Create new Country</h2>
    <form:form action="/user_manager/create_country" method="post" modelAttribute="country">
        <p><form:label path="name">Name</form:label></p>
        <p><form:errors path="name" cssStyle="color: darkred"/></p>
        <p><form:input path="name"/></p>
        <p><input type="submit" value="Create Country"></p>
    </form:form>
</body>
</html>
