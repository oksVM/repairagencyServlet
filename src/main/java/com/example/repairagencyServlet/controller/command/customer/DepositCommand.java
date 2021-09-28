package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.exception.UserNotFoundException;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class DepositCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest req) {
        AppUserService userService = new AppUserServiceImpl();
        if (req.getParameter("money") == null || req.getParameter("money").equals("")) {
            try {
                req.setAttribute("user", userService.findById(CommandUtility.getCurrentUserId(req)));
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            logger.info("Customer deposit form");
            return "/WEB-INF/customer/deposit.jsp";
        }

        Integer money = Integer.valueOf(req.getParameter("money"));

        if (money <= 0) {
            logger.info("Invalid money value");
            return "redirect:/repairagencyServlet/customer/deposit?error=true";
        }
        userService.updateDeposit(money, CommandUtility.getCurrentUserId(req));
        logger.info("Successful payment");
        return "redirect:/repairagencyServlet/customer/deposit?success=true";
    }
}
