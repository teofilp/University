package com.example.jsp.database;

import com.mysql.cj.jdbc.Driver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcProvider {
    public static JdbcOperations getJdbcOperations() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(getDataSource());

        return template;
    }

    private static DataSource getDataSource() {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(Driver.class.getName());
        source.setUsername("root");
        source.setPassword("Formula123");
        source.setUrl("jdbc:mysql://localhost:3306/Forum");
        source.setInitialSize(2);

        return source;
    }
}
