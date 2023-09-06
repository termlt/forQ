<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="css/index.css">
<!DOCTYPE html>
<html>
<head>
    <title>forQ Home</title>
</head>
<body>
<div class="navbar">
    <h1>forQ Forum</h1>
    <% if (session.getAttribute("loggedIn") == null || !(Boolean)session.getAttribute("loggedIn")) { %>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <a href="#">Register</a>
    <% } %>
</div>

<h1>asd</h1>
</body>
</html>
