package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.model.entity.Area;
import com.example.repairagencyServlet.model.entity.Order;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("categories", Area.values());
        OrderService orderService = new OrderServiceImpl();

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if (name == null || name.equals("") ||
                description == null || description.equals("")
        ) {
            return "/WEB-INF/customer/neworder.jsp";
        }

        Area area = Area.valueOf(req.getParameter("category"));
        Order order = new Order();
        order.setOrderName(name);
        order.setArea(area);
        order.setOrderDescription(description);
        try {
            orderService.save(order, req);
        }catch (RuntimeException e){
            System.out.println("err");
            return "redirect:/repairagencyServlet/customer/create_order";
        }
        return "redirect:/repairagencyServlet/customer/create_order";
    }

}
