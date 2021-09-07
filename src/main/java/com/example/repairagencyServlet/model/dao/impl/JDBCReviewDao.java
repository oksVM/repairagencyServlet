package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.model.dao.ReviewDao;
import com.example.repairagencyServlet.model.entity.Review;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCReviewDao implements ReviewDao {
    private Connection connection;

    public JDBCReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public Optional findById(long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<Review> findAllByMasterId(Long masterId) {
        return null;
    }
}
