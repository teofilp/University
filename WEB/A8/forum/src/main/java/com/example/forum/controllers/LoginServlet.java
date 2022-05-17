package com.example.forum.controllers;

import com.example.forum.domain.User;
import com.example.forum.repositories.UsersRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="LoginServlet", value="/login-servlet")
public class LoginServlet extends HttpServlet {
    private final UsersRepository repository = new UsersRepository();

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("Hello login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = repository.findByUsername(username);

        PrintWriter out = response.getWriter();

        if (user == null) {
            out.println("User not found!");
            return;
        }

        if (user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            out.println("Correct!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        out.println("Invalid password");
    }
}
