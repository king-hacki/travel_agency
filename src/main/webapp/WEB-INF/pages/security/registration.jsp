<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2>User Registration</h2>
    <div>
    <form:form action="/registration" method="post" modelAttribute="user">
        <p><form:label path="firstName">First name: </form:label></p>
        <p><form:errors path="firstName" cssStyle="color: darkred"/></p>
        <p><form:input path="firstName"/></p>
        <p><form:label path="lastName">Last name: </form:label></p>
        <p><form:errors path="lastName" cssStyle="color: darkred"/></p>
        <p><form:input path="lastName"/></p>
        <p><form:label path="email">Email: </form:label></p>
        <p><form:errors path="email" cssStyle="color: darkred"/></p>
        <p><form:input path="email"/></p>
        <p><form:label path="password">Password: </form:label></p>
        <p><form:errors path="password" cssStyle="color: darkred"/></p>
        <p><form:input path="password"/></p>
        <p><input type="submit" value="User Registration"></p>
        <p><input type="reset" value="Reset"></p>
    </form:form>
    </div>
</body>
</html>
