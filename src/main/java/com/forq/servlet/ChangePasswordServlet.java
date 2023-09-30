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
public class ChangePasswordServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String password = req.getParameter("newPassword");
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        userManager.changePassword(password, userId);
        resp.sendRedirect("/profile");
    }
}
