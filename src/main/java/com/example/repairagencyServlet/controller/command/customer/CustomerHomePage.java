package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class CustomerHomePage implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/customer/customer_homepage.jsp";
    }
}
