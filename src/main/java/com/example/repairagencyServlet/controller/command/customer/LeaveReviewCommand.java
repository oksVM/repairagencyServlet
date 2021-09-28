package com.example.repairagencyServlet.controller.command.customer;

import com.example.repairagencyServlet.controller.command.Command;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.service.ReviewService;
import com.example.repairagencyServlet.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LeaveReviewCommand implements Command {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public String execute(HttpServletRequest request) {
        ReviewService reviewService = new ReviewServiceImpl();
        try {
            request.setAttribute("reviews", reviewService.leaveFeedback(request.getParameter("review"),
                    Long.parseLong(request.getParameter("id"))));
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("Review has been left");
        return "/WEB-INF/admin/masterreviews.jsp";
    }
}
