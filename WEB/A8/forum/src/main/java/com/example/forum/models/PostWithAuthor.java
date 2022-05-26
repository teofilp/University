package com.example.forum.models;

import com.example.forum.domain.Post;

import java.util.List;

public class PostWithAuthor extends Post {
    private String authorName;
    private List<CommentWithAuthor> comments;

    public PostWithAuthor(int id, int authorId, String title, String content, String authorName, List<CommentWithAuthor> comments, String category) {
        super(id, authorId, title, content, category);
        this.authorName = authorName;
        this.comments = comments;
    }

    public String getAuthorName() {
        return authorName;
    }
}
