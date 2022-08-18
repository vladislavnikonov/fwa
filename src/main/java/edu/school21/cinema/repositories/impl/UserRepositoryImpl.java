package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userRowMapper;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, RowMapper<User> userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Integer save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO users (first_name, last_name, email, password) VALUES(?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            return ps;
        }, keyHolder);
        return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email = ?", userRowMapper, email)
                .stream()
                .findAny()
                .orElse(null);
    }
}
