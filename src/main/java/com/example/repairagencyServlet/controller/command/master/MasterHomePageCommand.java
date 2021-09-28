package com.example.repairagencyServlet.controller.command.master;

import com.example.repairagencyServlet.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class MasterHomePageCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Customer homepage");
        return "/WEB-INF/master/master_homepage.jsp";
    }
}
