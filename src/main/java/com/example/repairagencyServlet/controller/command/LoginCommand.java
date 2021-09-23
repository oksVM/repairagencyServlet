package com.example.repairagencyServlet.controller.command;

import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.service.AppUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command{
    private AppUserService userService;

    public LoginCommand(AppUserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            request.setAttribute("error", request.getParameter("error")!=null);
            request.setAttribute("logout", request.getParameter("logout")!=null);
            request.setAttribute("registered", request.getParameter("registered")!=null);
            return("/WEB-INF/account/login.jsp");
        }

        String login = request.getParameter("username");

        if(CommandUtility.checkUserIsLogged(request, login)){
            return "/noAccess.jsp";
        }

        AppUser user;
        try {
            user = userService.loadUserByUsername(login);
            if (!request.getParameter("password").equals(user.getPassword())) {
                throw new UsernameNotFoundException("user not found");
            }
        }
        catch (UsernameNotFoundException ex){
            return("redirect:profile/login?error");
        }

        CommandUtility.setUserSession(request, user);

        switch (user.getRole()) {
            case ADMIN:
                return "redirect:admin-panel";
            case CUSTOMER:
                return "redirect:profile";
            case MASTER:
                return "redirect:"
            default:
                return("/WEB-INF/account/login.jsp");
        }

    }
}
