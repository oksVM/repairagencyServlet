package com.example.repairagencyServlet.model.entity;


import java.time.OffsetDateTime;


public class Order {

    private Long id;
    private String orderName;
    private Area area;
    private String orderDescription;
    private Integer price;
    private OrderStatus orderStatus;
    private AppUser customer;
    private AppUser master;
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

    public static class Builder {
        private final Order order;

        public Builder() {
            this.order = new Order();
        }

        public Builder id(long id) {
            order.id = id;
            return this;
        }

        public Builder orderName(String orderName) {
            order.orderName = orderName;
            return this;
        }

        public Builder orderDescription(String orderDescription) {
            order.orderDescription = orderDescription;
            return this;
        }

        public Builder orderArea(Area area) {
            order.area = area;
            return this;
        }

        public Builder orderCustomer(AppUser user) {
            order.customer = user;
            return this;
        }

        public Builder orderMaster(AppUser user) {
            order.master = user;
            return this;
        }

        public Builder orderDate(OffsetDateTime offsetDateTime) {
            order.offsetDateTime = offsetDateTime;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            order.orderStatus = orderStatus;
            return this;
        }

        public Builder orderPrice(Integer price) {
            order.price = price;
            return this;
        }

        public Order build() {
            return order;
        }
    }
}

