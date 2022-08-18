package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

public interface UserRepository {

    Integer save(User user);

    User getUserByEmail(String email);
}
