
package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.UserNotFoundException;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CustomerDepositCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        AppUserService userService = new AppUserServiceImpl();
        Long id = Long.parseLong(req.getParameter("id"));
        if (req.getParameter("money") == null || req.getParameter("money").equals("")) {
            try {
                req.setAttribute("user", userService.findById(id));
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            return "/WEB-INF/admin/customerdeposit.jsp";
        }

        Integer money = Integer.valueOf(req.getParameter("money"));

        if (money <= 0) {
            return "redirect:/repairagencyServlet/admin/deposit?id="+id+"&error=true";
        }
        userService.updateDeposit(money, id);
        return "redirect:/repairagencyServlet/admin/deposit?id="+id+"&success=true";
    }
}

