package com.example.repairagencyServlet.model.service.impl;


import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.dao.impl.JDBCReviewDao;
import com.example.repairagencyServlet.model.entity.Review;
import com.example.repairagencyServlet.model.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public List<Review> findAllReviewsByMasterId(Long id) {
        try (JDBCReviewDao dao = (JDBCReviewDao) daoFactory.createReviewDao()) {
            List<Review> result = dao.findAllByMasterId(id);
            return result;
        }
    }

    @Override
    public int leaveFeedback(String feedback, Long orderId) throws OrderNotFoundException {
        try (JDBCReviewDao dao = (JDBCReviewDao) daoFactory.createReviewDao()) {
            int result = dao.save(feedback, orderId);
            return result;
        }
    }
}
