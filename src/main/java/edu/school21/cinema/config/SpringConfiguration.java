package edu.school21.cinema.config;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.AuthenticationRepository;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.impl.AuthenticationRepositoryImpl;
import edu.school21.cinema.repositories.impl.UserRepositoryImpl;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Time;

@Configuration
@PropertySource(value = "../application.properties", encoding = "UTF-8")
public class SpringConfiguration {
    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.driver-class-name}")
    private String driver;

    @Value("${datasource.username}")
    private String userName;

    @Value("${datasource.password}")
    private String password;

    @Value("${storage.path}")
    private String imagesPath;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public RowMapper<User> userRowMapper() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            return new User(id, firstName, lastName, email, password);
        };
    }

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate, RowMapper<User> userRowMapper) {
        return new UserRepositoryImpl(jdbcTemplate, userRowMapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserService(userRepository, passwordEncoder);
    }

    @Bean
    public AuthenticationRepository authenticationRepository(JdbcTemplate jdbcTemplate, RowMapper<Authentication> dataRowMapper) {
        return new AuthenticationRepositoryImpl(jdbcTemplate, dataRowMapper);
    }

    @Bean
    public RowMapper<Authentication> authenticationRowMapper() {
        return (resultSet, i) -> {
            String ip = resultSet.getString("ip");
            Date date = resultSet.getDate("date");
            Time time = resultSet.getTime("time");
            return new Authentication(ip, date, time);
        };
    }

    @Bean
    public AuthenticationService authenticationService(AuthenticationRepository authenticationRepository) {
        return new AuthenticationService(authenticationRepository);
    }

    @Bean
    public ImageService imageService() {
        return new ImageService(imagesPath);
    }

}
