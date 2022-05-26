package com.example.forum.controllers;

import com.example.forum.domain.Post;
import com.example.forum.models.CommentWithAuthor;
import com.example.forum.models.PostWithAuthor;
import com.example.forum.repositories.CommentsRepository;
import com.example.forum.repositories.PostsRepository;
import com.example.forum.repositories.UsersRepository;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="ForumServlet", value="/forum-servlet")
public class ForumServlet extends HttpServlet {
    private PostsRepository postsRepository = new PostsRepository();
    private UsersRepository usersRepository = new UsersRepository();
    private CommentsRepository commentsRepository = new CommentsRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String category = req.getParameter("category");
        int userId = Integer.parseInt(req.getParameter("userId"));

        boolean ok = postsRepository.add(new Post(userId, title, content, category));
        if (!ok) {
            resp.getWriter().println(false);
            return;
        }

        resp.getWriter().println(true);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");

        List<PostWithAuthor> posts = postsRepository.getByCategory(category).stream().map(x -> {
            String authorName = usersRepository.findById(x.getAuthorId()).getUsername();

            List<CommentWithAuthor> comments = commentsRepository.getByPostId(x.getId()).stream().map(y -> {
                String commentAuthor = usersRepository.findById(y.getAuthorId()).getUsername();

                return new CommentWithAuthor(y.getId(), y.getAuthorId(), y.getPostId(),y.getText(), commentAuthor);
            }).collect(Collectors.toList());

            return new PostWithAuthor(x.getId(), x.getAuthorId(), x.getTitle(), x.getContent(), authorName, comments, x.getCategory());
        }).collect(Collectors.toList());

        resp.setContentType("application/json");
        resp.getWriter().println(new Gson().toJson(posts));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postId = Integer.parseInt(req.getParameter("postId"));

        boolean ok = postsRepository.delete(postId);

        if (!ok) {
            resp.getWriter().println(false);
            return;
        }

        resp.getWriter().println(true);
    }
}
