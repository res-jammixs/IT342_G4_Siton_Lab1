package com.citu.backend.controller;

import com.citu.backend.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthenticated"));
        }
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(Map.of(
                "userId", user.getUserId(),
                "firstName", user.getFirstName(),
                "middleName", user.getMiddleName(),
                "lastName", user.getLastName(),
                "email", user.getEmail(),
                "phoneNumber", user.getPhoneNumber(),
                "isActive", user.isActive(),
                "createdAt", user.getCreatedAt()
        ));
    }
}
