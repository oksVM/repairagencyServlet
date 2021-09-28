package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
//TODO page
public class GetAllMastersCommand implements Command {
    AppUserService userService = new AppUserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("masters", userService.findAllMasters());
        return "/WEB-INF/admin/allmasters.jsp"  ;
    }
}
