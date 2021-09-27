package com.example.repairagencyServlet.controller.command.master;

import com.example.repairagencyServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class MasterHomePageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/master/master_homepage.jsp";
    }
}
