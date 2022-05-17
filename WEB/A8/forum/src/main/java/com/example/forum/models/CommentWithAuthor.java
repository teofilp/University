package com.example.forum.models;

import com.example.forum.domain.Comment;

public class CommentWithAuthor extends Comment {
    private String authorName;

    public CommentWithAuthor(int id, int authorId, int postId, String text, String authorName) {
        super(id, authorId, postId, text);
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
