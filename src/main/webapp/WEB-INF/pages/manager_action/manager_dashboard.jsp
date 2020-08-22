<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Dashboard</title>
</head>
<body>
    <%@include file="../../static/header.html"%>
    <form action="/user_manager/create_country">
        <input type="submit" value="Create new Country">
    </form>
    <form action="/user_manager/all_users">
        <input type="submit" value="All Users">
    </form>
</body>
</html>
