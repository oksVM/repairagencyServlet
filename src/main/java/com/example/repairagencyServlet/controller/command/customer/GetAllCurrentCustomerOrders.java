package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetAllCurrentCustomerOrders implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        request.setAttribute("orders",orderService.findAllCurrentCustomerOrders(CommandUtility.getCurrentUserId(request)));
        logger.info("All customer orders");
        return "/WEB-INF/customer/allorders.jsp";
    }
}
