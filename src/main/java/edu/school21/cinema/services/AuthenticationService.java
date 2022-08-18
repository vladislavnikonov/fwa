package edu.school21.cinema.services;

import edu.school21.cinema.models.Authentication;
import edu.school21.cinema.repositories.AuthenticationRepository;

import java.util.List;

public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public void save(String remoteAddr, Integer id) {
        authenticationRepository.save(remoteAddr, id);
    }

    public List<Authentication> getAuthenticationByUserId(Integer userId) {
        return authenticationRepository.getAuthenticationByUserId(userId);
    }
}
