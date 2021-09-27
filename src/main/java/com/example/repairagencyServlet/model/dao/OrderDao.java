package com.example.repairagencyServlet.model.dao;

import com.example.repairagencyServlet.controller.dto.PriceDto;
import com.example.repairagencyServlet.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends GenericDAO{
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findAllByCustomerId(Long id);
    List<Order> findAllByMasterId(Long id);
    Order setPrice(PriceDto price, Long id);
    Order payForOrder(Long id);
    Order setMaster(Long masterId, Long orderId);
    Order takeInWork(Long id);
    Order markAsDone(Long id);
}
