package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.model.entity.Area;
import com.example.repairagencyServlet.model.entity.Order;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest req) {
        req.setAttribute("categories", Area.values());
        OrderService orderService = new OrderServiceImpl();

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if (name == null || name.equals("") ||
                description == null || description.equals("")
        ) {
            logger.info("Form for new order");
            return "/WEB-INF/customer/neworder.jsp";
        }

        Area area = Area.valueOf(req.getParameter("category"));
        Order order = new Order.Builder()
                .orderName(name)
                .orderDescription(description)
                .orderArea(area)
                .build();

        orderService.save(order, req);
        logger.info("Order has been saved");
        return "redirect:/repairagencyServlet/customer/create_order?success=true";
    }

}
