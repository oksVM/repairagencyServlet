package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.model.dao.OrderDao;
import com.example.repairagencyServlet.model.entity.Area;
import com.example.repairagencyServlet.model.entity.Order;
import com.example.repairagencyServlet.model.entity.OrderStatus;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
        List<Order> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select o.id, o.area, o.offset_data_time, o.order_name, o.order_status, o.price, u.email " +
                        "from orders o left join app_user u on u.app_user_id=master_id;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
               order.setArea(Area.valueOf(rs.getString("area")));
                order.setOffsetDateTime(OffsetDateTime.ofInstant(((Timestamp)rs.getObject("offset_data_time")).toInstant(), ZoneId.of("UTC")));
                order.setOrderName(rs.getString("order_name"));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
                order.setPrice(rs.getInt("price"));
                //order.setMaster(rs.);

                list.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
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
