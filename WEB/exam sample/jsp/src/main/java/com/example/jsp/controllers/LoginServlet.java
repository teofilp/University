package com.example.jsp.controllers;

import com.example.jsp.domain.User;
import com.example.jsp.repository.UsersRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private UsersRepository repository;

    public LoginServlet() {
        repository = new UsersRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = repository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            response.getWriter().println("Invalid login attempt");
            return;
        }

        request.getSession().setAttribute("userId", user.getId());
        response.getWriter().println("Login was successful!");
    }
}
