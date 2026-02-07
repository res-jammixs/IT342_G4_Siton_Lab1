package com.citu.backend.controller;

import com.citu.backend.entity.User;
import com.citu.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final AuthService authService;

    public ProfileController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<?> viewProfile(@RequestHeader(value = "Authorization", required = false) String authorization) {
        String token = null;
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }
        if (token == null || token.isBlank()) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthenticated"));
        }
        Optional<User> user = authService.validateUser(token);
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
        }
        User u = user.get();
        
        Map<String, Object> response = new java.util.HashMap<>();
        response.put("userId", u.getUserId());
        response.put("firstName", u.getFirstName());
        response.put("middleName", u.getMiddleName() != null ? u.getMiddleName() : "");
        response.put("lastName", u.getLastName());
        response.put("email", u.getEmail());
        response.put("phoneNumber", u.getPhoneNumber() != null ? u.getPhoneNumber() : "");
        response.put("isActive", u.isActive());
        response.put("createdAt", u.getCreatedAt());
        
        return ResponseEntity.ok(response);
    }
}
