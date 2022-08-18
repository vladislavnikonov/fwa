<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" scope="session" type="edu.school21.cinema.models.User"/>
<jsp:useBean id="authentications" scope="session" type="java.util.List"/>
<html>
<head>
    <title>Profile</title>
    <style>
        table {
            width: 400px;
            border-collapse: collapse;
        }

        td, th {
            padding: 3px;
            border: 1px solid black;
        }

        th {
            background: gray;
        }

        div {
            float: left;
            padding: 20px;
        }
    </style>
</head>
<body>

<div>
    <c:if test="${avatar != null}">
        <img src="data:image/jpg;base64,${avatar}" alt="Avatar" width="400px" height="500px">
    </c:if>
    <c:if test="${error != null}">
        <h1>${error}</h1>
    </c:if>
    <h2>
        ${user.firstName}
        ${user.lastName}
    </h2>
    <h3>
        ${user.email}
    </h3>
</div>

<div>
    <table>
        <tr>
            <th>Date</th>
            <th>Time</th>
            <th>IP</th>
        </tr>
        <c:forEach var="auth" items="${authentications}">
            <tr>
                <td>${auth.date}</td>
                <td>${auth.time}</td>
                <td>${auth.ip}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
    <table>
        <tr>
            <th>File name</th>
            <th>Size (KB)</th>
            <th>MIME</th>
        </tr>
        <c:if test="${images != null}">
            <c:forEach var="img" items="${images}">
                <tr>
                    <td>
                        <a href='<c:url value="/images/${img.file.name}" />' target="_blank">${img.imageName}</a>
                    </td>
                    <td>${img.size}</td>
                    <td>${img.MIME}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <form enctype="multipart/form-data" method="post" action="/fwa/images">
        <p>
            <label for="image_uploads">Choose image to upload (PNG, JPG)</label>
            <input type="file" id="image_uploads" name="image" accept=".jpg, .jpeg, .png">
            <input type="submit" value="Upload">
        </p>
    </form>
</div>

</body>
</html>
