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

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String new_username = req.getParameter("new_username");
        String new_password = req.getParameter("new_password");

        if (userManager.getUserByUsername(new_username) == null) {
            User user = new User();
            user.setUsername(new_username);
            user.setPassword(new_password);
            java.util.Date currentDate = new java.util.Date();
            user.setCreationDate(new java.sql.Date(currentDate.getTime()));

            userManager.addUser(user);

            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", true);
            User loggedInUser = userManager.getUserByUsernameAndPassword(new_username, new_password);
            session.setAttribute("user", loggedInUser);
            resp.sendRedirect("/index.jsp");
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }
}
