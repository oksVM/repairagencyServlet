package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.controller.config.PasswordConfig;
import com.example.repairagencyServlet.model.dao.OrderDao;
import com.example.repairagencyServlet.model.entity.Area;
import com.example.repairagencyServlet.model.entity.Order;
import com.example.repairagencyServlet.model.entity.OrderStatus;

import javax.servlet.http.HttpServletRequest;
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
        List<Order> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select id, area, offset_data_time, order_name, order_status, price " +
                        "from orders where customer_id = ?;")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setArea(Area.valueOf(rs.getString("area")));
                order.setOffsetDateTime(OffsetDateTime.ofInstant(((Timestamp)rs.getObject("offset_data_time")).toInstant(), ZoneId.of("UTC")));
                order.setOrderName(rs.getString("order_name"));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
                order.setPrice(rs.getInt("price"));

                list.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    @Override
    public List<Order> findAllByMasterId(Long id) {
        List<Order> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select id, area, offset_data_time, order_name, order_status, price " +
                        "from orders where master_id = ?;")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("id"));
                order.setArea(Area.valueOf(rs.getString("area")));
                order.setOffsetDateTime(OffsetDateTime.ofInstant(((Timestamp)rs.getObject("offset_data_time")).toInstant(), ZoneId.of("UTC")));
                order.setOrderName(rs.getString("order_name"));
                order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
                order.setPrice(rs.getInt("price"));

                list.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int save(Order order, HttpServletRequest request) {
        String INSERT_Order_SQL="INSERT INTO orders " +
                "(order_name, order_description, area, order_status, customer_id, offset_data_time ) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;

        try(PreparedStatement preparedStatement=connection.prepareStatement(INSERT_Order_SQL)) {
            preparedStatement.setString(1, order.getOrderName());
            preparedStatement.setString(2, order.getOrderDescription());
            preparedStatement.setString(3, order.getArea().name());
            preparedStatement.setString(4, OrderStatus.WAIT_FOR_ADMIN_CONFIRMATION.name());
            preparedStatement.setLong(5, CommandUtility.getCurrentUserId(request));
            preparedStatement.setObject(6, OffsetDateTime.now());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e){
        }
        return result;
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
}
