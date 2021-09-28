package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetOneOrderCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        AppUserService appUserService = new AppUserServiceImpl();
        try {
            request.setAttribute("order", orderService.findOrderById(Long.parseLong(request.getParameter("id"))));
            request.setAttribute("masters", appUserService.findAllMasters());
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Order handling with admin");
        return "/WEB-INF/admin/orderdetails.jsp";
    }
}
