package com.forq.servlet;

import com.forq.manager.PostManager;
import com.forq.model.Post;
import com.forq.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/add-new-post")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostManager postManager = new PostManager();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String title = req.getParameter("postTitle");
        String description = req.getParameter("postDescription");


        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setDescription(description);
        newPost.setUpvotes(0);
        newPost.setDownvotes(0);
        newPost.setUser(user);

        postManager.addPost(newPost);

        resp.sendRedirect("/");
    }
}
