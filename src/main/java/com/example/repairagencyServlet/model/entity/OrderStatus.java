package com.example.repairagencyServlet.model.entity;


public enum OrderStatus {
    WAIT_FOR_ADMIN_CONFIRMATION,
    WAIT_FOR_PAYMENT,
    PAID,
    WAIT_FOR_MASTER_CONFIRMATION,
    CANCELED,
    IN_WORK,
    DONE,
    REVIEWED;
}
