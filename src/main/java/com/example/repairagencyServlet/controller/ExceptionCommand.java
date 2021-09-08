package com.example.repairagencyServlet.controller;

import com.example.repairagencyServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
