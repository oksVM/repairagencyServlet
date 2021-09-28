package com.example.repairagencyServlet.model.dao;

import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDAO {
    Optional<Order> findById(Long id);

    List<Order> findAll();

    List<Order> findAllByCustomerId(Long id);

    List<Order> findAllByMasterId(Long id);

    int setPrice(Integer price, Long id) throws OrderNotFoundException;

    int payForOrder(Long id, Long userId) throws OrderNotFoundException;

    int setMaster(Long masterId, Long orderId) throws OrderNotFoundException;

    int takeInWork(Long id) throws OrderNotFoundException;

    int markAsDone(Long id) throws OrderNotFoundException;
}
