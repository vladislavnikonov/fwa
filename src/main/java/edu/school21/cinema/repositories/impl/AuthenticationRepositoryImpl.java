package edu.school21.cinema.repositories.impl;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.repositories.AuthenticationRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.List;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Authentication> authenticationRowMapper;

    public AuthenticationRepositoryImpl(JdbcTemplate jdbcTemplate, RowMapper<Authentication> authenticationRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.authenticationRowMapper = authenticationRowMapper;
    }

    @Override
    public void save(String ip, Integer userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        jdbcTemplate.update("INSERT INTO authentications (date, time, ip, user_id) VALUES (?, ?, ?, ?)",
                localDateTime, localDateTime, ip, userId);
    }

    @Override
    public List<Authentication> getAuthenticationByUserId(Integer userId) {
        return jdbcTemplate.query("SELECT * FROM authentications where user_id = ?", authenticationRowMapper, userId);
    }
}
