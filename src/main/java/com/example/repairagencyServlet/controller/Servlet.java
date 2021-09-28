package com.example.repairagencyServlet.controller;


import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.ExceptionCommand;
import com.example.repairagencyServlet.controller.command.LogOutCommand;
import com.example.repairagencyServlet.controller.command.LoginCommand;
import com.example.repairagencyServlet.controller.command.admin.*;
import com.example.repairagencyServlet.controller.command.customer.*;
import com.example.repairagencyServlet.controller.command.master.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


@WebServlet(name = "/", value = "/")
public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private final Logger logger = LogManager.getLogger(this.getClass());

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers",
                        new HashSet<String>());

        commands.put("/repairagencyServlet/registration", new CustomerRegistrationCommand());
        commands.put("/repairagencyServlet/logout", new LogOutCommand());
        commands.put("/repairagencyServlet/login", new LoginCommand());
        commands.put("/repairagencyServlet/exception", new ExceptionCommand());

        commands.put("/repairagencyServlet/admin", new AdminHomePageCommand());
        commands.put("/repairagencyServlet/admin/master_registration", new MasterRegistrationCommand());
        commands.put("/repairagencyServlet/admin/masters", new GetAllMastersCommand());
        commands.put("/repairagencyServlet/admin/reviews", new GetMasterReviewsCommand());
        commands.put("/repairagencyServlet/admin/customers", new GetAllCustomersCommand());
        commands.put("/repairagencyServlet/admin/deposit", new CustomerDepositCommand());
        commands.put("/repairagencyServlet/admin/orders", new GetAllOrderCommand());
        commands.put("/repairagencyServlet/admin/order", new GetOneOrderCommand());
        commands.put("/repairagencyServlet/admin/order/price", new SetPriceForOrderCommand());
        commands.put("/repairagencyServlet/admin/order/master", new SetMasterForOrderCommand());

        commands.put("/repairagencyServlet/customer", new CustomerHomePageCommand());
        commands.put("/repairagencyServlet/customer/deposit", new DepositCommand());
        commands.put("/repairagencyServlet/customer/create_order", new CreateOrderCommand());
        commands.put("/repairagencyServlet/customer/orders", new GetAllCurrentCustomerOrders());
        commands.put("/repairagencyServlet/customer/order", new GetCustomerOrderCommand());
        commands.put("/repairagencyServlet/customer/order/pay", new PayForOrderCommand());
        commands.put("/repairagencyServlet/customer/order/review", new LeaveReviewCommand());

        commands.put("/repairagencyServlet/master", new MasterHomePageCommand());
        commands.put("/repairagencyServlet/master/orders", new GetAllCurrentMasterOrdersCommand());
        commands.put("/repairagencyServlet/master/order", new GetOrderCommand());
        commands.put("/repairagencyServlet/master/order/in_work", new TakeOrderInWorkCommand());
        commands.put("/repairagencyServlet/master/order/done", new MarkOrderAsDoneCommand());

        logger.info("Mapping completed");
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        Command command = commands.getOrDefault(path,
                (r) -> "/WEB-INF/view/homepage.jsp");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
