package com.citu.backend.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.citu.backend.entity.AuthToken;
import com.citu.backend.entity.User;
import com.citu.backend.repository.AuthTokenRepository;
import com.citu.backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthTokenRepository authTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, AuthTokenRepository authTokenRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authTokenRepository = authTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String firstName, String middleName, String lastName, String email, String rawPassword, String phoneNumber) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        String hashed = passwordEncoder.encode(rawPassword);
        User user = new User(firstName, middleName, lastName, email, hashed, phoneNumber, true);
        return userRepository.save(user);
    }

    public String login(String email, String rawPassword) {
        Optional<User> u = userRepository.findByEmail(email);
        if (u.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        User user = u.get();
        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        String token = UUID.randomUUID().toString();
        AuthToken authToken = new AuthToken(token, user);
        authTokenRepository.save(authToken);
        return token;
    }

    public Optional<User> findByToken(String token) {
        return authTokenRepository.findByToken(token).map(AuthToken::getUser);
    }

    public User registerUser(String firstName, String middleName, String lastName, String email, String rawPassword, String phoneNumber) {
        return register(firstName, middleName, lastName, email, rawPassword, phoneNumber);
    }

    public String loginUser(String email, String rawPassword) {
        return login(email, rawPassword);
    }

    public Optional<User> validateUser(String token) {
        return findByToken(token);
    }

    public void logoutUser(String token) {
        authTokenRepository.deleteByToken(token);
    }
}
