package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
//TODO
public class GetAllCurrentCustomerOrders implements Command {
    OrderService orderService = new OrderServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders",orderService.findAllCurrentCustomerOrders(CommandUtility.getCurrentUserId(request)));
        return "/WEB-INF/customer/allorders.jsp";
    }
}
