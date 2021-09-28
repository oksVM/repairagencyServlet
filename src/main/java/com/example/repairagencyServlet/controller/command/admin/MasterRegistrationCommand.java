package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MasterRegistrationCommand implements Command {
    @Override

    public String execute(HttpServletRequest req) {
        AppUserService userService = new AppUserServiceImpl();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (firstName == null || firstName.equals("") ||
                lastName == null || lastName.equals("") ||
                email == null || email.equals("") ||
                password == null || password.equals("")
        ) {
            return "/WEB-INF/admin/masterregistration.jsp";
        }

        AppUser user = new AppUser.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(Role.CUSTOMER).build();

        if (validate(user, req)) {
            req.setAttribute("firstName",firstName);
            req.setAttribute("lastName",lastName);
            req.setAttribute("email",email);
            return "/WEB-INF/admin/masterregistration.jsp";
        }

        try {
            userService.saveNewMaster(user);
        }
        catch (UserAlreadyExistAuthenticationException ex) {
            return "redirect:/repairagencyServlet/admin/master_registration?error=true" ;
        }
        return "redirect:/repairagencyServlet/admin/master_registration?registered=true";
    }

    private boolean validate(AppUser user, HttpServletRequest req) {
        Map<String, Boolean> fieldsValidity = new HashMap<>();

        ResourceBundle bundle =
                ResourceBundle.getBundle("properties/regexes", new Locale(CommandUtility.getCurrentUserLanguage(req)));

        fieldsValidity.put("invalidEmail", !user.getEmail().matches(bundle.getString("email.regexp")));
        fieldsValidity.put("invalidFirstName", !user.getFirstName().matches(bundle.getString("name.regexp")));
        fieldsValidity.put("invalidLastName", !user.getLastName().matches(bundle.getString("name.regexp")));
        fieldsValidity.put("invalidPassword", !user.getPassword().matches(bundle.getString("password.regexp")));

        fieldsValidity.forEach(req::setAttribute);

        return fieldsValidity.values().stream().anyMatch(val->val);
    }

}
