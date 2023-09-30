package com.forq.servlet;

import com.forq.manager.PostManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet
public class ChangePostTitleServlet extends HttpServlet {
    PostManager postManager = new PostManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postManager.changePostTitle(req.getParameter("newTitle"), Integer.parseInt(req.getParameter("post_id")));

        resp.sendRedirect("/");
    }
}
