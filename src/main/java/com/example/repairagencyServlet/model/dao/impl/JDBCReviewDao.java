package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.dao.ReviewDao;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Review;
import com.example.repairagencyServlet.model.mapper.ReviewMapper;
import com.example.repairagencyServlet.model.service.OrderService;
import com.example.repairagencyServlet.model.service.impl.OrderServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCReviewDao implements ReviewDao {
    private Connection connection;

    public JDBCReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(String feedback, Long orderId) throws OrderNotFoundException {
        String INSERT_Order_SQL = "INSERT INTO review " +
                "(review_description, master_id ) VALUES (?, ?)";
        int result = 0;
        OrderService orderService = new OrderServiceImpl();
        try {
            connection.setAutoCommit(false);
            AppUser appUser = orderService.findOrderById(orderId).getMaster();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Order_SQL)) {
                preparedStatement.setString(1, feedback);
                preparedStatement.setLong(2, appUser.getId());
                result = preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Review> findAllByMasterId(Long id) {
        List<Review> list = new ArrayList<>();
        Map<Long, Review> cache = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select * from review  where master_id = ?;")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            ReviewMapper reviewMapper = new ReviewMapper();
            while (rs.next()) {
                Review order = reviewMapper.extractFromResultSet(rs);
                list.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
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
}
