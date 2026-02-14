package com.citu.backend.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citu.backend.entity.User;
import com.citu.backend.service.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public static class RegisterRequest {
        @NotBlank(message = "First name is required")
        public String firstName;
        
        public String middleName;
        
        @NotBlank(message = "Last name is required")
        public String lastName;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        public String email;
        
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        public String password;
        
        public String phoneNumber;
    }

    public static class LoginRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        public String email;
        
        @NotBlank(message = "Password is required")
        public String password;
    }

    public static class LogoutRequest {
        public String token;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        try {
            User u = authService.registerUser(req.firstName, req.middleName, req.lastName, req.email, req.password, req.phoneNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("userId", u.getUserId(), "email", u.getEmail()));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            String token = authService.loginUser(req.email, req.password);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authorization,
                                    @RequestBody(required = false) LogoutRequest req) {
        String token = null;
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        } else if (req != null && req.token != null && !req.token.isBlank()) {
            token = req.token;
        }
        if (token == null || token.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing token"));
        }
        authService.logoutUser(token);
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
}
