package com.example.repairagencyServlet.model.service.impl;

import com.example.repairagencyServlet.exception.NotEnoughMoneyException;
import com.example.repairagencyServlet.exception.OrderNotFoundException;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.dao.impl.JDBCOrderDao;
import com.example.repairagencyServlet.model.entity.Order;
import com.example.repairagencyServlet.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class OrderServiceImpl implements OrderService {
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
   public Order setPrice(Integer price, Long id) throws OrderNotFoundException {
       try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
           Order order = dao.setPrice(price, id).orElseThrow(OrderNotFoundException::new);
           return order;
       }
   }

    @Override
    public Order payForOrder(Long id) throws NotEnoughMoneyException, OrderNotFoundException {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.payForOrder(id).orElseThrow(OrderNotFoundException::new);
            return order;
        }
    }

    @Override
    public Order setMaster(Long masterId, Long orderId) throws OrderNotFoundException {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.setMaster(masterId, orderId).orElseThrow(OrderNotFoundException::new);;
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
    public Order takeInWork(Long id) throws OrderNotFoundException {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.takeInWork(id).orElseThrow(OrderNotFoundException::new);
            return order;
        }
    }

    @Override
    public Order markAsDone(Long id) throws OrderNotFoundException {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            Order order = dao.markAsDone(id).orElseThrow(OrderNotFoundException::new);
            return order;
        }
    }
}

