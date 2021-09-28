package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SetMasterForOrderCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest req) {

        OrderService orderService = new OrderServiceImpl();
        Long id = Long.parseLong(req.getParameter("id"));
        Long masterId = Long.parseLong(req.getParameter("master"));

        try {
            orderService.setMaster(masterId, id);
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Master assigned");
        return "redirect:/repairagencyServlet/admin/order?id=" + id + "&successMaster=true";
    }
}
