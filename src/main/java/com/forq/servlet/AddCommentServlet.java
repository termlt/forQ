package com.forq.servlet;

import com.forq.manager.CommentManager;
import com.forq.manager.PostManager;
import com.forq.model.Comment;
import com.forq.model.Post;
import com.forq.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet
public class AddCommentServlet extends HttpServlet {
    CommentManager commentManager = new CommentManager();
    PostManager postManager = new PostManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Comment comment = new Comment();
        comment.setText(req.getParameter("comment-text"));
        comment.setUser(user);

        int postId = Integer.parseInt(req.getParameter("postId"));
        Post post = postManager.getPostById(postId);
        comment.setPost(post);

        commentManager.addComment(comment);

        resp.sendRedirect("/post?postId=" + Integer.parseInt(req.getParameter("postId")));

        //  http:..../post/id
        // /post/1
    }
}