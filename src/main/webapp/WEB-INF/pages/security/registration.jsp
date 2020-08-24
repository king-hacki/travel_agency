<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2>User Registration</h2>
    <form:form action="/registration" method="post" modelAttribute="user">
        <table>

            <td><form:label path="firsName">First name: </form:label></td>
            <td><form:input path="firsName"/></td>

            <td><form:label path="lastName">Last name: </form:label></td>
            <td><form:input path="lastName"/></td>

            <td><form:label path="email">Email: </form:label></td>
            <td><form:input path="email"/></td>

            <td><form:label path="password">Password: </form:label></td>
            <td><form:input path="password"/></td>

            <td><input type="submit" value="User Registration"></td>
            <td><input type="reset" value="Reset"></td>
        </table>
    </form:form>
</body>
</html>
