<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Image</title>
</head>
<body>
<c:if test="${error != null}">
    <p>${error}</p>
</c:if>
<img src="data:image/jpg;base64,${image}" alt="Avatar">
</body>
</html>
