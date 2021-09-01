package com.example.repairagencyServlet.controller.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordConfig {
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder(10);
        }
    }

