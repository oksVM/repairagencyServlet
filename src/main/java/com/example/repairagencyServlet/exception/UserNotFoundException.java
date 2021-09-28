package com.example.repairagencyServlet.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User doesn't exist.");
    }
}
