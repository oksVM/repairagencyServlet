package com.example.repairagencyServlet.model.service;

import com.example.repairagencyServlet.controller.dto.PriceDto;
import com.example.repairagencyServlet.exception.NotEnoughMoneyException;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.dao.impl.JDBCAppUserDao;
import com.example.repairagencyServlet.model.dao.impl.JDBCOrderDao;
import com.example.repairagencyServlet.model.entity.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;


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
    public List<Order> findAllCurrentCustomerOrders() {
        return null;
    }

    @Override
    public List<Order> findAllOrders() {
        try (JDBCOrderDao dao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            List<Order> orderList = dao.findAll();
            return orderList;
        }
    }

    @Override
    public Order findOrderById(Long id) {
        return null;
    }

   @Override
   public Order setPrice(PriceDto price, Long id) {
       Order order = findOrderById(id);

       return order;
   }

    @Override
    public Order payForOrder(Long id) throws NotEnoughMoneyException {
        Order order = findOrderById(id);

        return order;
    }

    @Override
    public Order setMaster(Long masterId, Long id) {
        Order order = findOrderById(id);
        return order;
    }

    @Override
    public List<Order> findAllCurrentMasterOrders() {
        return null;
    }

    @Override
    public Order takeInWork(Long id) {
        Order order = findOrderById(id);

        return order;
    }

    @Override
    public Order markAsDone(Long id) {
        Order order = findOrderById(id);

        return order;
    }
}

