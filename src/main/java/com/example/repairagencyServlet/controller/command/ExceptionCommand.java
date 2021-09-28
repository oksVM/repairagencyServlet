package com.example.repairagencyServlet.controller.command;

import javax.servlet.http.HttpServletRequest;
//TODO
public class ExceptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Exception");
    }
}
