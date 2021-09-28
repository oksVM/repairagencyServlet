package com.example.repairagencyServlet.controller.command;

import com.example.repairagencyServlet.exception.UserNotFoundException;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest request) {
        AppUserService userService = new AppUserServiceImpl();
        if (request.getMethod().equals("GET")) {
            request.setAttribute("error", request.getParameter("error") != null);
            request.setAttribute("logout", request.getParameter("logout") != null);
            request.setAttribute("registered", request.getParameter("registered") != null);
            logger.info("Login form");
            return ("/WEB-INF/login.jsp");
        }

        String username = request.getParameter("username");

        if (CommandUtility.checkUserIsLogged(request, username)) {
            logger.info("Access denied");
            return "/WEB-INF/noAccess.jsp";
        }

        AppUser user;
        try {
            user = userService.loadUserByEmail(username, request.getParameter("password"));
            if (user == null) {
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException ex) {
            logger.info("User not found");
            return ("redirect:/repairagencyServlet/login?error=true");
        }

        CommandUtility.setUserSession(request, user);

        switch (user.getRole()) {
            case ADMIN:
                logger.info("Admin has been logged");
                return "redirect:/repairagencyServlet/admin";
            case CUSTOMER:
                logger.info("Customer has been logged");
                return "redirect:/repairagencyServlet/customer";
            case MASTER:
                logger.info("Master has been logged");
                return "redirect:/repairagencyServlet/master";
            default:
                return ("/WEB-INF/login.jsp");
        }
    }
}
