package com.example.forum.controllers;

import com.example.forum.repositories.PostsRepository;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="HelloServlet", value="/hello-servlet")
public class HelloServlet extends HttpServlet {
    private PostsRepository repository = new PostsRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> categories = repository.getAllCategories();
        response.setContentType("application/json");
        response.getWriter().println(new Gson().toJson(categories));
    }

    public void destroy() {
    }
}