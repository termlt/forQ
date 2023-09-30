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
public class ProfileServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userIdStr = req.getParameter("userId");
        if (userIdStr != null) {
            try {
                User user = userManager.getUserByUsername(userIdStr);

                req.setAttribute("user", user);
                req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/");
            }
        } else {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            req.setAttribute("user", user);
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
        }
    }
}
