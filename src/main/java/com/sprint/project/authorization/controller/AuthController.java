package com.sprint.project.authorization.controller;

import com.sprint.project.authorization.dto.LoginRequest;
import com.sprint.project.authorization.dto.LoginResponse;
import com.sprint.project.authorization.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // 🔥 important
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String role = authService.authenticate(
                request.getUsername(),
                request.getPassword()
        );

        if(role != null) {
            return ResponseEntity.ok(new LoginResponse(role));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}