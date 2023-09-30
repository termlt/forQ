<%@ page import="com.forq.manager.PostManager" %>
<%@ page import="com.forq.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.forq.model.User" %>
<%@ page import="com.forq.manager.CommentManager" %>
<%@ page import="com.forq.model.Comment" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="css/profile.css">
<!DOCTYPE html>
<html>
<head>
    <title>forQ Profile</title>
</head>
<body>
<div class="navbar">
    <h1>forQ Forum</h1>
    <a href="${pageContext.request.contextPath}/">Home</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</div>


<div class="post-container">
    <%
        PostManager postManager = new PostManager();
        User user = (User) request.getAttribute("user");
        List<Post> posts = postManager.getAllPostsByUser(user.getId());

        for (Post post : posts) {
    %>
    <div class="post">
        <h3 class="post-title"><%= post.getTitle() %>
        </h3>
        <p class="post-description"><%= post.getDescription() %>
        </p>
    </div>
    <%
        }
    %>
</div>


<div class="comments-section">
    <h2>Comments</h2>

    <%
        CommentManager commentManager = new CommentManager();
        List<Comment> comments = commentManager.getAllCommentsByUser(user.getId());

        for (Comment comment : comments) {

    %>

    <div class="comment">
        <p class="comment-text"><%=comment.getText()%></p>
    </div>

    <% } %>
</div>


</body>
</html>
