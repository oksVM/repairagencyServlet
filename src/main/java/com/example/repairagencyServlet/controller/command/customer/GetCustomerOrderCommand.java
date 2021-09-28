package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetCustomerOrderCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        try {
            request.setAttribute("order", orderService.findOrderById(Long.parseLong(request.getParameter("id"))));
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Order handling with customer");
        return "/WEB-INF/customer/orderdetails.jsp";
    }
}
