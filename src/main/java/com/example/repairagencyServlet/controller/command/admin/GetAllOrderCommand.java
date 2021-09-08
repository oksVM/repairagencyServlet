package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetAllOrderCommand implements Command {
    OrderService orderService = new OrderServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders",orderService.findAllOrders());
        return "/WEB-INF/admin/allorders.jsp";
    }
}
