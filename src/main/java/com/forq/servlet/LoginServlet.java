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

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userManager.getUserByUsernameAndPassword(username, password);

        if (user == null) resp.sendRedirect("/");
        else {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", true);
            session.setAttribute("user", user);
            resp.sendRedirect("/");
        }
    }
}
