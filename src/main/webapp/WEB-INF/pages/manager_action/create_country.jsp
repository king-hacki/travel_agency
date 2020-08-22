<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Create Country</title>
</head>
<body>
    <%@include file="../../static/header.html"%>
    <h2>Create new Country</h2>
    <form:form action="/user_manager/create_country" method="post" modelAttribute="country">
        <table>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
            <td><input type="submit" value="Create Country"></td>
        </table>
    </form:form>
</body>
</html>
