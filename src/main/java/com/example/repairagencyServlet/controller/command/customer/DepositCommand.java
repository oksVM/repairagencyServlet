package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class DepositCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        AppUserService userService = new AppUserServiceImpl();

        if (req.getParameter("money") == null || req.getParameter("money").equals("")) {
            return "/WEB-INF/customer/deposit.jsp";
        }
        Integer money = Integer.valueOf(req.getParameter("money"));
        try {
            userService.updateDeposit(money, CommandUtility.getCurrentUserId(req));
        }catch (RuntimeException e){
            return "redirect:/repairagencyServlet/customer/deposit?error";
        }
        return "redirect:/repairagencyServlet/customer/deposit";
    }
}
