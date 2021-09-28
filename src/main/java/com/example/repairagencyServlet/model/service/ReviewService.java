package com.example.repairagencyServlet.model.service;

import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.entity.Review;

import java.util.List;


public interface ReviewService {
    List<Review> findAllReviewsByMasterId(Long id);

    int leaveFeedback(String feedback, Long orderId) throws OrderNotFoundException;
}
