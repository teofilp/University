package com.example.forum.repositories;

import com.example.forum.database.JdbcProvider;
import com.example.forum.domain.User;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;

public class UsersRepository {
    private JdbcOperations jdbcOperations;

    public UsersRepository() {
        this.jdbcOperations = JdbcProvider.getJdbcOperations();
    }

    public User findByUsername(String username) {
        return jdbcOperations.query(String.format("SELECT * FROM Users Where Username = '%s'", username), (rs, i) -> {
            int id = rs.getInt("Id");
            String userName = rs.getString("Username");
            String password = rs.getString("Password");

            return new User(id, userName, password);
        }).parallelStream().findFirst().orElse(null);
    }

    public User findById(int id) {
        return jdbcOperations.query(String.format("SELECT * FROM Users Where Id = '%s'", id), (rs, i) -> {
            String userName = rs.getString("Username");
            String password = rs.getString("Password");

            return new User(id, userName, password);
        }).parallelStream().findFirst().orElse(null);
    }
}
