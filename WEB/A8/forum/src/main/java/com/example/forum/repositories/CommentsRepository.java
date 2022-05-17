package com.example.forum.repositories;

import com.example.forum.database.JdbcProvider;
import com.example.forum.domain.Comment;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class CommentsRepository {
    private JdbcOperations jdbcOperations;

    public CommentsRepository() {
        jdbcOperations = JdbcProvider.getJdbcOperations();
    }

    public List<Comment> getByPostId(int postId) {
        String query = String.format("SELECT * FROM Comments WHERE PostId = %d", postId);
        return jdbcOperations.query(query, (rs, i) -> {
            int id = rs.getInt("Id");
            int authorId = rs.getInt("AuthorId");
            String text = rs.getString("Text");

            return new Comment(id, authorId, postId, text);
        });
    }

    public boolean add(Comment comment) {
        String query = String.format("INSERT INTO Comments (PostId, AuthorId, Text) values(%d, %d, '%s')", comment.getPostId(), comment.getAuthorId(), comment.getText());
        return jdbcOperations.update(query) > 0;
    }

    public boolean delete(int commentId) {
        String query = String.format("DELETE FROM Comments WHERE Id = %d", commentId);
        return jdbcOperations.update(query) > 0;
    }
}
