package com.example.repairagencyServlet.model;


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


    @Override
    public String toString() {
        return orderStatus.toString();
    }
}

