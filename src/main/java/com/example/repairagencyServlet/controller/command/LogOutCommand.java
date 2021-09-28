package com.example.repairagencyServlet.controller.command;

import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public String execute(HttpServletRequest request) {
        request.getServletContext().removeAttribute("user");
        CommandUtility.removeUserSession(request.getServletContext(),
                ((AppUser) request.getSession().getAttribute("user")).getEmail());
        AppUser appUser = new AppUser();
        appUser.setId(0L);
        appUser.setRole(Role.UNKNOWN);
        CommandUtility.setUserSession(request, appUser);
        return "redirect:/repairagencyServlet/login?logout";
    }
}
