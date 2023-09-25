<%@ page import="com.forq.manager.CommentManager" %>
<%@ page import="com.forq.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.forq.manager.UserManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" type="text/css" href="css/post.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Open+Sans:wght@400;700&display=swap"
      rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
    <title>Post</title>
</head>
<body>
<div class="navbar">
    <h1>forQ Forum</h1>
</div>


<div class="post-container">
    <div class="post">
        <h3 class="post-title"><%= request.getAttribute("postTitle") %>
        </h3>
        <p class="post-description"><%= request.getAttribute("postDescription") %>
        </p>
        <p class="post-author">@<%= request.getAttribute("postAuthor")%>
        </p>
    </div>
</div>


<div class="comments-section">
    <h2>Comments</h2>

    <%
        UserManager userManager = new UserManager();
        CommentManager commentManager = new CommentManager();
        List<Comment> comments = commentManager.getAllCommentsOfPost(Integer.parseInt(request.getParameter("postId")));

        for (Comment comment : comments) {

    %>

    <div class="comment">
        <p class="comment-text"><%=comment.getText()%></p>
        <p class="comment-author"><%=userManager.getUsernameById(comment.getUser().getId())%></p>
    </div>

    <% } %>

    <%
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) { %>
    <form id="comment-form" method="post"
          action="${pageContext.request.contextPath}/add-comment?postId=<%=request.getParameter("postId")%>">
        <label for="comment-text"></label><textarea id="comment-text" name="comment-text"
                                                    placeholder="Write your comment..."
                                                    rows="4"></textarea>
        <button type="submit">Submit</button>
    </form>
    <% } %>
</div>


</body>
</html>
