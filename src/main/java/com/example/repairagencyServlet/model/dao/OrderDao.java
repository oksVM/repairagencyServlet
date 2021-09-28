package com.example.repairagencyServlet.model.dao;

import com.example.repairagencyServlet.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDAO{
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findAllByCustomerId(Long id);
    List<Order> findAllByMasterId(Long id);
    Optional<Order> setPrice(Integer price, Long id);
    Optional<Order> payForOrder(Long id);
    Optional<Order> setMaster(Long masterId, Long orderId);
    Optional<Order> takeInWork(Long id);
    Optional<Order> markAsDone(Long id);
}
