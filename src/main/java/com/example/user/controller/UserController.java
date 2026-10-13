package com.example.user.controller;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestBody User input) {

        User user = repo.findByUsername(input.getUsername());

        if (user == null ||
            !user.getPassword().equals(input.getPassword())) {
            return ResponseEntity.status(401)
                    .body("Invalid username or password");
        }

        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername()
        ));
    }
}