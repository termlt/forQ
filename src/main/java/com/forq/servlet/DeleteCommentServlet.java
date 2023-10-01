package com.forq.servlet;

import com.forq.manager.CommentManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet
public class DeleteCommentServlet extends HttpServlet {
    CommentManager commentManager = new CommentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commentManager.deleteComment(Integer.parseInt(req.getParameter("commentId")));

        resp.sendRedirect("/profile");
    }
}
