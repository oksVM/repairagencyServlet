package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.controller.command.CommandUtility;
import com.example.repairagencyServlet.model.dao.OrderDao;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Order;
import com.example.repairagencyServlet.model.entity.OrderStatus;
import com.example.repairagencyServlet.model.mapper.AppUserMapper;
import com.example.repairagencyServlet.model.mapper.OrderMapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.*;


public class JDBCOrderDao implements OrderDao {
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Order> findById(Long id) {
            try (PreparedStatement ps = connection.prepareStatement("select * from orders where id = ?")) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                OrderMapper orderMapper = new OrderMapper();
                if (!rs.next()) {
                    return Optional.empty();
                }
                Order order = orderMapper.extractFromResultSet(rs);
                return Optional.of(order);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        Map<Long, AppUser> cache = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select o.*, u.app_user_id, u.email " +
                        "from orders o left join app_user u on u.app_user_id=master_id;")) {
        OrderMapper orderMapper = new OrderMapper();
        AppUserMapper userMapper = new AppUserMapper();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = orderMapper.extractFromResultSet(rs);
                order.setMaster(userMapper.makeUnique(cache, new AppUser.Builder().id(rs.getLong("email")).email(rs.getString("email")).build()));
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
        Map<Long, AppUser> cache = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select o.*, u.app_user_id, u.email " +
                        "from orders o left join app_user u on o.master_id = u.app_user_id where customer_id = ?;")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            OrderMapper orderMapper = new OrderMapper();
            AppUserMapper userMapper = new AppUserMapper();
            while (rs.next()) {
                Order order = orderMapper.extractFromResultSet(rs);
                order.setMaster(userMapper.makeUnique(cache, new AppUser.Builder().id(rs.getLong("email")).email(rs.getString("email")).build()));
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
            OrderMapper orderMapper = new OrderMapper();
            while (rs.next()) {
                Order order = orderMapper.extractFromResultSet(rs);
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
    public Optional<Order> setPrice(Integer price, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> payForOrder(Long id) {
        return null;
    }

    @Override
    public Optional<Order> setMaster(Long masterId, Long orderId) {
        return null;
    }

    @Override
    public Optional<Order> takeInWork(Long id) {
        return null;
    }

    @Override
    public Optional<Order> markAsDone(Long id) {
        return null;
    }

    @Override
    public void create(Object entity) {}

    @Override
    public Optional findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(Object entity) {}

    @Override
    public void delete(long id) {}

    @Override
    public void close() {}
}
