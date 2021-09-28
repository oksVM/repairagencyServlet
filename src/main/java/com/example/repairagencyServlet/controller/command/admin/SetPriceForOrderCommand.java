package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SetPriceForOrderCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest req) {

        OrderService userService = new OrderServiceImpl();
        Long id = Long.parseLong(req.getParameter("id"));

        Integer price = Integer.valueOf(req.getParameter("price"));

        if (price <= 0) {
            logger.info("Invalid price value");
            return "redirect:/repairagencyServlet/admin/order?id=" + id + "&errorPrice=true";
        }
        try {
            userService.setPrice(price, id);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Price assigned");
        return "redirect:/repairagencyServlet/admin/order?id=" + id + "&successPrice=true";
    }
}
