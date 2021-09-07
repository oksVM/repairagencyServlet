package com.example.repairagencyServlet.model.dao;

import com.example.repairagencyServlet.model.entity.Review;
import java.util.List;

public interface ReviewDao extends GenericDAO{
    List<Review> findAllByMasterId(Long masterId);
}
