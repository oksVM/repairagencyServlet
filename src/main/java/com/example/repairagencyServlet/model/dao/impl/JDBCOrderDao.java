package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.model.dao.OrderDao;
import com.example.repairagencyServlet.model.entity.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
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
    public void update(Object entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAllByCustomerId(Long id) {
        return null;
    }

    @Override
    public List<Order> findAllByMasterId(Long id) {
        return null;
    }
}
