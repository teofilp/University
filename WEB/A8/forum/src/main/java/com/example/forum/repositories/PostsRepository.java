package com.example.forum.repositories;

import com.example.forum.database.JdbcProvider;
import com.example.forum.domain.Post;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class PostsRepository {
    private JdbcOperations jdbcOperations;

    public PostsRepository() {
        this.jdbcOperations = JdbcProvider.getJdbcOperations();
    }

    public boolean add(Post post) {
        String query = String.format("INSERT INTO Posts (AuthorId, Title, Content, Category) values(%d, '%s', '%s', '%s')", post.getAuthorId(), post.getTitle(), post.getContent(), post.getCategory());
        return this.jdbcOperations.update(query) > 0;
    }

    public List<Post> getAll() {
        String query = "SELECT * FROM Posts";
        return this.jdbcOperations.query(query, (rs, i) -> {
           int id = rs.getInt("Id");
           int authorId = rs.getInt("AuthorId");
           String title = rs.getString("Title");
           String content = rs.getString("Content");
           String category = rs.getString("Category");

            return new Post(id, authorId, title, content, category);
        });
    }

    public List<Post> getByCategory(String category) {
        String query = String.format("SELECT * FROM Posts WHERE Category = '%s'", category);
        return this.jdbcOperations.query(query, (rs, i) -> {
            int id = rs.getInt("Id");
            int authorId = rs.getInt("AuthorId");
            String title = rs.getString("Title");
            String content = rs.getString("Content");

            return new Post(id, authorId, title, content, category);
        });
    }

    public boolean delete(int postId) {
        String query = String.format("DELETE FROM Posts WHERE Id = %d", postId);
        return this.jdbcOperations.update(query) > 0;
    }

    public List<String> getAllCategories() {
        String query = String.format("SELECT DISTINCT Category FROM Posts");
        return this.jdbcOperations.query(query, (rs, i) ->  rs.getString("Category"));
    }
}
