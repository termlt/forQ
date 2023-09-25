<%@ page import="com.forq.manager.PostManager" %>
<%@ page import="com.forq.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.forq.manager.UserManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="css/index.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Open+Sans:wght@400;700&display=swap"
      rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
    <title>forQ Home</title>
</head>
<body>
<div class="navbar">
    <h1>forQ Forum</h1>
    <% if (session.getAttribute("loggedIn") == null || !(Boolean) session.getAttribute("loggedIn")) { %>
    <a onclick="toggleLoginForm()">Login</a>
    <a onclick="toggleRegisterForm()">Register</a>
    <% } else { %>
    <a href="${pageContext.request.contextPath}/profile">Profile</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <%}%>
</div>

<div class="overlay" id="loginOverlay">
    <div class="login-container">
        <span class="close-button" onclick="toggleLoginForm()">&times;</span>
        <h2>Login</h2>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="login-button"><span>Login</span></button>
        </form>
    </div>
</div>


<div class="overlay" id="registerOverlay">
    <div class="login-container">
        <span class="close-button" onclick="toggleRegisterForm()">&times;</span>
        <h2>Register</h2>
        <form method="post" action="${pageContext.request.contextPath}/register" onsubmit="return validatePassword()">
            <div class="form-group">
                <label for="new_username">Username:</label>
                <input type="text" id="new_username" name="new_username" required>
            </div>
            <div class="form-group">
                <label for="new_password">Password:</label>
                <input type="password" id="new_password" name="new_password" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <div id="passwordMismatch" style="color: red; display: none;">Passwords do not match.</div>
            <button type="submit" class="register-button"><span>Register</span></button>
        </form>
    </div>
</div>


<%
    Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
    if (loggedIn != null && loggedIn) { %>
<a href="#" class="new-post-button" onclick="togglePostForm()">Create New Post</a>
<% } %>

<div class="post-form" id="postForm">
    <span class="close-button" onclick="togglePostForm()">&times;</span>
    <h2>Create a New Post</h2>
    <form method="post" action="${pageContext.request.contextPath}/add-new-post">
        <div class="form-group">
            <label for="postTitle">Post Title:</label>
            <input type="text" id="postTitle" name="postTitle" required>
        </div>
        <div class="form-group">
            <label for="postDescription">Description:</label>
            <textarea id="postDescription" name="postDescription" rows="4" required></textarea>
        </div>
        <button type="submit">Submit</button>
    </form>
</div>


<div class="post-container">
    <%
        PostManager postManager = new PostManager();
        UserManager userManager = new UserManager();
        List<Post> posts = postManager.getAllPosts();

        for (Post post : posts) {
    %>
    <div class="post" onclick="location.href='${pageContext.request.contextPath}/post?postId=<%=post.getId()%>';">
        <h3 class="post-title"><%= post.getTitle() %>
        </h3>
        <p class="post-description"><%= post.getDescription() %>
        </p>
        <p class="post-author">@<%= userManager.getUsernameById(post.getUser().getId())%>
        </p>
    </div>
    <%
        }
    %>
</div>


<script>
    function toggleLoginForm() {
        let overlay = document.getElementById("loginOverlay");
        if (overlay.style.display === "block") {
            overlay.style.display = "none";
        } else {
            overlay.style.display = "block";
        }
    }

    function toggleRegisterForm() {
        let overlay = document.getElementById("registerOverlay");
        if (overlay.style.display === "block") {
            overlay.style.display = "none";
        } else {
            overlay.style.display = "block";
        }
    }

    function togglePostForm() {
        let overlay = document.getElementById("postForm");
        overlay.style.display = (overlay.style.display === "block") ? "none" : "block";
    }


    function validatePassword() {
        var newPassword = document.getElementById("new_password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (newPassword !== confirmPassword) {
            document.getElementById("passwordMismatch").style.display = "block";
            return false;
        } else {
            document.getElementById("passwordMismatch").style.display = "none";
            return true;
        }
    }
</script>
</body>
</html>
