<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>

<c:if test="${error != null}">
    <p>${error}</p>
</c:if>
<h1>Authentication</h1>
<form action="/fwa/signIn" method="POST">
    <label>Email: </label>
    <input type="email" placeholder="Enter email" name="email" required>
    <label>Password: </label>
    <input type="password" placeholder="Enter password" name="password" required>
    <button type="submit">Sign In</button>
</form>
</body>
</html>
