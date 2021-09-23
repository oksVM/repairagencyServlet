package com.example.repairagencyServlet.controller.command;

import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getServletContext().removeAttribute("user");
        CommandUtility.removeUserSession(request.getServletContext(),
                ((AppUser) request.getSession().getAttribute("user")).getEmail());
        CommandUtility.setUserSession(request, new AppUser(0L, Role.UNKNOWN));
        return "redirect:/repairagencyServlet/login?logout";
    }
}
