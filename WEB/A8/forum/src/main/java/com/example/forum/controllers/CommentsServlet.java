package com.example.forum.controllers;

import com.example.forum.domain.Comment;
import com.example.forum.repositories.CommentsRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CommentsServlet", value = "/comments-servlet")
public class CommentsServlet extends HttpServlet {
    private CommentsRepository repository = new CommentsRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        int postId = Integer.parseInt(request.getParameter("postId"));
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        String text = request.getParameter("text");

        boolean ok = repository.add(new Comment(authorId, text, postId));

        if (!ok) {
            response.getWriter().println(false);
            return;
        }

        response.getWriter().println(true);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        int commentId = Integer.parseInt(req.getParameter("commentId"));

        boolean ok = repository.delete(commentId);

        if (!ok) {
            resp.getWriter().println(false);
            return;
        }

        resp.getWriter().println(true);
    }
}
