package com.forq.servlet;

import com.forq.manager.UserManager;
import com.forq.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet
public class ChangeUsernameServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("newUsername");
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        userManager.changeUsername(username, userId);
        resp.sendRedirect("/profile");
    }
}
