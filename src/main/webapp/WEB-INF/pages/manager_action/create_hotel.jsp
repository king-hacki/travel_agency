<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Create Hotel</title>
</head>
<body>
    <%@include file="../../static/header.html"%>
    <h3>Create new Hotel in Country: ${countryName}</h3>
    <form:form action="/user_manager/create_hotel/${countryId}" method="post" modelAttribute="hotel">
        <table>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name"/></td>
            <td><input type="submit" value="Create Hotel"></td>
        </table>
    </form:form>
</body>
</html>
