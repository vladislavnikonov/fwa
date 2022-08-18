package edu.school21.cinema.services;

import edu.school21.cinema.exception.IncorrectPasswordException;
import edu.school21.cinema.exception.UserAlreadyExistException;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Integer save(User user) throws UserAlreadyExistException {
        if (userRepository.getUserByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else
            throw new UserAlreadyExistException("Email already exists");
    }

    public User getUserByEmail(String email, String password) throws UsernameNotFoundException, IncorrectPasswordException {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        return user;
    }
}
