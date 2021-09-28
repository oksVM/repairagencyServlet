package com.example.repairagencyServlet.model.mapper;

import com.example.repairagencyServlet.model.entity.Area;
import com.example.repairagencyServlet.model.entity.Order;
import com.example.repairagencyServlet.model.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        return new Order.Builder()
                .id(rs.getLong("id"))
                .orderName(rs.getString("order_name"))
                .orderArea(Area.valueOf(rs.getString("area")))
                .orderDescription(rs.getString("order_description"))
                .orderStatus(OrderStatus.valueOf(rs.getString("order_status")))
                .orderPrice(rs.getInt("price"))
                .orderDate(OffsetDateTime.ofInstant(((Timestamp) rs.getObject("offset_data_time")).toInstant(), ZoneId.of("UTC")))
                .build();
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order order) {
        cache.putIfAbsent(order.getId(), order);
        return cache.get(order.getId());
    }
}
