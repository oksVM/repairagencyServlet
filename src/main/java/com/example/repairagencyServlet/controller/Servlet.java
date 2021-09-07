package com.example.repairagencyServlet.controller;


import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.controller.command.customer.CustomerRegistrationCommand;
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
/*        commands.put("/main",
                new MainCommand());
        commands.put("/registration",
                new RegistrationCommand());
        commands.put("/logout",
                new LogOutCommand());
        commands.put("/login",
                new LoginCommand());
        commands.put("/exception",
                new ExceptionCommand());*/


        logger.info("Mapping configured");
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
        //TODO
        System.out.println("path: " + path);
        Command command = commands.getOrDefault(path,
                (r) -> "/index.jsp");
        //TODO
        String page = command.execute(request);
        System.out.println("page: " + page);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
