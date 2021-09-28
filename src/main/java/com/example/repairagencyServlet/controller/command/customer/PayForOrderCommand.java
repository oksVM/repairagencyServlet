package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.exception.NotEnoughMoneyException;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PayForOrderCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest req) {

        OrderService userService = new OrderServiceImpl();
        Long id = Long.parseLong(req.getParameter("id"));

        try {
            try {
                userService.payForOrder(id, CommandUtility.getCurrentUserId(req));
            } catch (NotEnoughMoneyException e) {
                logger.info("Not enought money");
                return "redirect:/repairagencyServlet/customer/order?id=" + id + "&errorPay=true";
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Order has been paid");
        return "redirect:/repairagencyServlet/admin/order?id=" + id + "&successPay=true";
    }
}
