package com.example.repairagencyServlet.model.service;

import com.example.repairagencyServlet.controller.dto.PriceDto;
import com.example.repairagencyServlet.exception.NotEnoughMoneyException;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.dao.impl.JDBCOrderDao;
import com.example.repairagencyServlet.model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class OrderServiceImpl implements OrderService{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public int save(Order order, HttpServletRequest request) {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            int result = dao.save(order, request);
            return result;
        }
    }

    @Override
    public List<Order> findAllCurrentCustomerOrders(Long id) {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            List<Order> orderList = dao.findAllByCustomerId(id);
            return orderList;
        }
    }

    @Override
    public List<Order> findAllOrders() {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            List<Order> orderList = dao.findAll();
            return orderList;
        }
    }

    @Override
    public Order findOrderById(Long id) throws OrderNotFoundException {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.findById(id).orElseThrow(OrderNotFoundException::new);
            return order;
        }
    }

   @Override
   public Order setPrice(PriceDto price, Long id) {
       try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
           Order order = dao.setPrice(price, id);
           return order;
       }
   }

    @Override
    public Order payForOrder(Long id) throws NotEnoughMoneyException {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.payForOrder(id);
            return order;
        }
    }

    @Override
    public Order setMaster(Long masterId, Long orderId) {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.setMaster(masterId, orderId);
            return order;
        }
    }

    @Override
    public List<Order> findAllCurrentMasterOrders(Long id) {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            List<Order> orderList = dao.findAllByMasterId(id);
            return orderList;
        }
    }

    @Override
    public Order takeInWork(Long id) {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.takeInWork(id);
            return order;
        }
    }

    @Override
    public Order markAsDone(Long id) {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.markAsDone(id);
            return order;
        }
    }
}

