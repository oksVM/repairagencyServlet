package com.example.repairagencyServlet.model.service;


import com.example.repairagencyServlet.exception.NotEnoughMoneyException;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    int save(Order order, HttpServletRequest request);
    List<Order> findAllCurrentCustomerOrders(Long id);
    List<Order> findAllOrders();
    Order findOrderById(Long id) throws OrderNotFoundException;
    Order setPrice(Integer price, Long id) throws OrderNotFoundException;
    Order payForOrder(Long id) throws NotEnoughMoneyException, OrderNotFoundException;
    Order setMaster(Long masterId, Long id) throws OrderNotFoundException;
    List<Order> findAllCurrentMasterOrders(Long id);
    Order takeInWork(Long id) throws OrderNotFoundException;
    Order markAsDone(Long id) throws OrderNotFoundException;
}

