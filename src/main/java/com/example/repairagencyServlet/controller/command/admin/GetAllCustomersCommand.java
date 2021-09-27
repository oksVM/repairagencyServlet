
package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetAllCustomersCommand implements Command {
    AppUserService userService = new AppUserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("customers", userService.findAllCustomers());
        return "/WEB-INF/admin/allcustomers.jsp"  ;
    }
}
