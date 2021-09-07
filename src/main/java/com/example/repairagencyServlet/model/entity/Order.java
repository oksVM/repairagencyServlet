package com.example.repairagencyServlet.model.entity;


import com.example.repairagencyServlet.model.dao.DaoFactory;

import javax.persistence.*;
import java.time.OffsetDateTime;


public class Order {

    private Long id;
    private String orderName;
    @Enumerated(value = EnumType.STRING)
    private Area area;
    private String orderDescription;
    private Integer price;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private AppUser customer;
    private AppUser master;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private OffsetDateTime offsetDateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public AppUser getCustomer() {
        return customer;
    }

    public void setCustomer(AppUser customer) {
        this.customer = customer;
    }

    public AppUser getMaster() {
        return master;
    }

    public void setMaster(AppUser master) {
        this.master = master;
    }

    public OffsetDateTime getOffsetDateTime() {
        return offsetDateTime;
    }

    public void setOffsetDateTime(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    @Override
    public String toString() {
        return orderStatus.toString();
    }
}

