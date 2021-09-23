package com.example.repairagencyServlet.model.service;


import com.example.repairagencyServlet.controller.dto.PriceDto;
import com.example.repairagencyServlet.exception.NotEnoughMoneyException;
import com.example.repairagencyServlet.model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    int save(Order order, HttpServletRequest request);
    List<Order> findAllCurrentCustomerOrders();
    List<Order> findAllOrders();
    Order findOrderById(Long id);
    Order setPrice(PriceDto price, Long id);
    Order payForOrder(Long id) throws NotEnoughMoneyException;
    Order setMaster(Long masterId, Long id);
    List<Order> findAllCurrentMasterOrders();
    Order takeInWork(Long id);
    Order markAsDone(Long id);
}

