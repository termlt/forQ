package com.forq.servlet;

import com.forq.manager.PostManager;
import com.forq.model.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet
public class GetPostServlet extends HttpServlet {
    PostManager postManager = new PostManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postIdStr = req.getParameter("postId");

        if (postIdStr != null) {
            try {
                int postId = Integer.parseInt(postIdStr);
                Post post = postManager.getPostById(postId);

                req.setAttribute("postTitle", post.getTitle());
                req.setAttribute("postDescription", post.getDescription());
                req.setAttribute("postAuthor", post.getUser().getUsername());
                req.getRequestDispatcher("/post.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/");
        }
    }
}
