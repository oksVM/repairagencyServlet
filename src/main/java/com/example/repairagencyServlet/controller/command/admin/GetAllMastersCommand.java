package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.model.service.AppUserService;
import com.example.repairagencyServlet.model.service.impl.AppUserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetAllMastersCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest request) {
        AppUserService userService = new AppUserServiceImpl();
        request.setAttribute("masters", userService.findAllMasters());
        logger.info("All masters page");
        return "/WEB-INF/admin/allmasters.jsp"  ;
    }
}
