package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminHomePage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/admin_homepage.jsp";
    }
}
