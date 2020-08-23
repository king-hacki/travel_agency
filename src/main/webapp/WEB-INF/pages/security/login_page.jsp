<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>
    <c:if test="${param.error ne null}">
        Username or password is not correct!
    </c:if>
    <form action="/login" method="post">
        <table>
            <tr>
                <td><label for="username">Email: </label></td>
                <td><input type="text" name="username" id="username"></td>
            </tr>
            <tr>
                <td><label for="password">Password: </label></td>
                <td><input type="password" name="password" id="password"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Sign In">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
