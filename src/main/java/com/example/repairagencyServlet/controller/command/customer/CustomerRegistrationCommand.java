package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CustomerRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        AppUserService appUserService = new AppUserServiceImpl();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (firstName == null || firstName.equals("") ||
                lastName == null || lastName.equals("") ||
                email == null || email.equals("") ||
                password == null || password.equals("")
        ) {
            return "/WEB-INF/registration.jsp";
        }

        boolean haveErrors = false;

        if(!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")){
            req.setAttribute("emailIncorrect",true);
            haveErrors = true;
        }

        if(!password.matches("^.{4,16}$")){
            req.setAttribute("passwordIncorrect",true);
            haveErrors = true;
        }

        if(haveErrors){
            req.setAttribute("firstName",firstName);
            req.setAttribute("lastName",lastName);
            req.setAttribute("email",email);
            return "/WEB-INF/registration.jsp";
        }
        AppUser appUser = new AppUser();
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setPassword(password);
        try {
            appUserService.saveNewCustomer(appUser);
        }catch (RuntimeException | UserAlreadyExistAuthenticationException e){
            return "redirect:/repairagencyServlet/registration?error=true";
        }
        return "redirect:/repairagencyServlet/registration";
    }
}
