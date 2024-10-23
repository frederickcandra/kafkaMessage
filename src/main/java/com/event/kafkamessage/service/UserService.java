package com.event.kafkamessage.service;

import com.event.kafkamessage.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    // Simpan data user secara lokal dalam sebuah Map
    private Map<String, User> userDatabase = new HashMap<>();

    public String register(User user) {
        if (userDatabase.containsKey(user.getUsername())) {
            return "Username already exists";
        }
        userDatabase.put(user.getUsername(), new User(user.getUsername(), user.getPassword()));
        return "User registered successfully";
    }

    public String login(User user) {
        User existingUser = userDatabase.get(user.getUsername());
        if (existingUser == null) {
            return "User not found";
        }
        if (!existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid password";
        }
        return "Login successful";
    }
}
