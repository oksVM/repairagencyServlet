package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.exception.UserNotFoundException;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;


public class DepositCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        AppUserService userService = new AppUserServiceImpl();
        if (req.getParameter("money") == null || req.getParameter("money").equals("")) {
            try {
                req.setAttribute("user", userService.findById(CommandUtility.getCurrentUserId(req)));
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            return "/WEB-INF/customer/deposit.jsp";
        }

        Integer money = Integer.valueOf(req.getParameter("money"));

        if (money <= 0) {
            return "redirect:/repairagencyServlet/customer/deposit?error=true";
        }
        userService.updateDeposit(money, CommandUtility.getCurrentUserId(req));
        return "redirect:/repairagencyServlet/customer/deposit?success=true";
    }
}
