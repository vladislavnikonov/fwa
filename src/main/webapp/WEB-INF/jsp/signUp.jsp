<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<c:if test="${error != null}">
    <p>${error}</p>
</c:if>
    <h1>Registration</h1>
    <form action="/fwa/signUp" method="POST">
        <label>FirstName: </label>
        <input type="text" placeholder="Enter first name" name="firstName" required>
        <label>LastName: </label>
        <input type="text" placeholder="Enter Last name" name="lastName" required>
        <label>Email: </label>
        <input type="email" placeholder="Enter email" name="email" required>
        <label>Password: </label>
        <input type="password" placeholder="Enter password" name="password" required>
        <button type="submit">Sign Up</button>
    </form>
</body>
</html>
