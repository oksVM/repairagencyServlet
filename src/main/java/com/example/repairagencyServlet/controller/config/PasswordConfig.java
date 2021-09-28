package com.example.repairagencyServlet.controller.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordConfig {
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

