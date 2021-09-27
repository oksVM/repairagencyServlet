package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetCustomerOrderCommand implements Command {
    OrderService orderService = new OrderServiceImpl();
    @Override
    public String execute(HttpServletRequest request){
        try {
            request.setAttribute("order",orderService.findOrderById(Long.parseLong(request.getParameter("id"))));
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/customer/orderdetails.jsp";
    }
}
