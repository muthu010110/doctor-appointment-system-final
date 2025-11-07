package com.example.demo.Controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Dto.LoginRequest;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        UserEntity u = service.login(req.getEmail(), req.getPassword());
        if (u == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }
        return ResponseEntity.ok(Map.of(
                "id", u.getId(),
                "name", u.getName(),
                "email", u.getEmail(),
                "role", u.getRole()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        UserEntity created = service.register(user);
        if (created == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Email Already Exist"));
        }
        return ResponseEntity.created(URI.create("/api/auth/users/" + created.getId()))
                .body(Map.of(
                        "id", created.getId(),
                        "name", created.getName(),
                        "email", created.getEmail(),
                        "role", created.getRole()
                ));
    }
}
