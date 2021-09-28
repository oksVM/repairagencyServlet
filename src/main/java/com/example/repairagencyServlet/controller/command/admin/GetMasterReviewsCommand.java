
package com.example.repairagencyServlet.controller.command.admin;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.model.service.ReviewService;
import com.example.repairagencyServlet.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class GetMasterReviewsCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public String execute(HttpServletRequest request) {
        ReviewService reviewService = new ReviewServiceImpl();
        request.setAttribute("reviews", reviewService.findAllReviewsByMasterId(Long.parseLong(request.getParameter("id"))));
        logger.info("Reviews page");
        return "/WEB-INF/admin/masterreviews.jsp";
    }
}

