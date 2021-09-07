package com.example.repairagencyServlet.model.service;

import com.example.repairagencyServlet.controller.dto.PriceDto;
import com.example.repairagencyServlet.exception.NotEnoughMoneyException;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.entity.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/*
@Service
public class OrderServiceImpl implements OrderService{
    private DaoFactory daoFactory = DaoFactory.getInstance();




    @Override
    public Order save(Order order) {
        return orderRepository.save(Order.builder()
                .orderName(order.getOrderName())
                .orderDescription(order.getOrderDescription())
                .area(order.getArea())
                .orderStatus(OrderStatus.WAIT_FOR_ADMIN_CONFIRMATION)
                .offsetDateTime(OffsetDateTime.now())
                .customer((AppUser) appUserService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()))
                .build());
    }


    @Override
    public List<Order> findAllCurrentCustomerOrders() {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return orderRepository
                .findAllByCustomerId(((AppUser) appUserService
                        .loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId(), pageable);
    }

    @Override
    public List<Order> findAllOrders() {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField):
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        //TODO
        if(keyWord!=null){
            return orderRepository.findAll(keyWord,pageable);
        }
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
    }

   @Override
   public Order setPrice(PriceDto price, Long id) {
       Order order = findOrderById(id);
       order.setPrice(price.getAmountOfMoney());
       order.setOrderStatus(OrderStatus.WAIT_FOR_PAYMENT);
       return order;
   }

    @Override
    public Order payForOrder(Long id) throws NotEnoughMoneyException {
        Order order = findOrderById(id);
        AppUser currentAppUser = ((AppUser) appUserService
                .loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        if(order.getPrice()>currentAppUser.getAmountOfMoney()){
            throw new NotEnoughMoneyException();
        }
        currentAppUser.setAmountOfMoney(currentAppUser.getAmountOfMoney()-order.getPrice());
        appUserRepository.save(currentAppUser);
        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order setMaster(Long masterId, Long id) {
        Order order = findOrderById(id);
        AppUser master = appUserService.findById(masterId);
        order.setMaster(master);
        order.setOrderStatus(OrderStatus.WAIT_FOR_MASTER_CONFIRMATION);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> findAllCurrentMasterOrders() {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return orderRepository
                .findAllByMasterId(((AppUser) appUserService
                        .loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())).getId(), pageable);
    }

    @Override
    public Order takeInWork(Long id) {
        Order order = findOrderById(id);
        order.setOrderStatus(OrderStatus.IN_WORK);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order markAsDone(Long id) {
        Order order = findOrderById(id);
        order.setOrderStatus(OrderStatus.DONE);
        orderRepository.save(order);
        return order;
    }
}
*/
