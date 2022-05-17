package com.example.forum.domain;

public class Post {
    private int id, authorId;
    private String title, content, category;

    public Post(int authorId, String title, String content, String category) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Post(int id, int authorId, String title, String content, String category) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
