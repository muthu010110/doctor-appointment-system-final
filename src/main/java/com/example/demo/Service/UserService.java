package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.Userrepository;

@Service
public class UserService {

    @Autowired
    private Userrepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity login(String email, String rawPassword) {
        Optional<UserEntity> opt = repo.findByEmail(email.trim().toLowerCase());
        if (opt.isEmpty()) return null;

        UserEntity user = opt.get();
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) return null;

        return user;
    }

    public UserEntity register(UserEntity user) {
        user.setEmail(user.getEmail().trim().toLowerCase());
        user.setRole(user.getRole() == null ? "PATIENT" : user.getRole());

        if (repo.existsByEmail(user.getEmail())) return null;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
