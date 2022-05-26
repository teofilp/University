package com.example.forum.domain;

public class Comment {
    int id, authorId, postId;
    String text;

    public Comment(int id, int authorId, int postId, String text) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.postId = postId;
    }

    public Comment(int authorId, String text, int postId) {
        this.authorId = authorId;
        this.text = text;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
