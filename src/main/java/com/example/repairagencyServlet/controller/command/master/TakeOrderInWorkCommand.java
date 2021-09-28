package com.example.repairagencyServlet.controller.command.master;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class TakeOrderInWorkCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest req) {

        OrderService orderService = new OrderServiceImpl();
        Long id = Long.parseLong(req.getParameter("id"));

        try {
            orderService.takeInWork(id);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Order has been taken in work");
        return "redirect:/repairagencyServlet/master/order?id=" + id + "&successInWork=true";
    }
}
