package com.example.repairagencyServlet.controller.command;

import com.example.repairagencyServlet.exception.UserNotFoundException;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
//TODO
public class LoginCommand implements Command{
    private AppUserService userService = new AppUserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            request.setAttribute("error", request.getParameter("error")!=null);
            request.setAttribute("logout", request.getParameter("logout")!=null);
            request.setAttribute("registered", request.getParameter("registered")!=null);
            return("/WEB-INF/login.jsp");
        }

        String username = request.getParameter("username");

        if(CommandUtility.checkUserIsLogged(request, username)){
            return "/WEB-INF/noAccess.jsp";
        }

        AppUser user;
        try {
                user = userService.loadUserByEmail(username, request.getParameter("password"));
            if (user==null){
                throw new UserNotFoundException();
            }
        }
        catch (UserNotFoundException ex){
            return("redirect:/repairagencyServlet/login?error=true");
        }

        CommandUtility.setUserSession(request, user);

        switch (user.getRole()) {
            case ADMIN:
                return "redirect:/repairagencyServlet/admin";
            case CUSTOMER:
                return "redirect:/repairagencyServlet/customer";
            case MASTER:
                return "redirect:/repairagencyServlet/master";
            default:
                return("/WEB-INF/login.jsp");
        }
    }
}
