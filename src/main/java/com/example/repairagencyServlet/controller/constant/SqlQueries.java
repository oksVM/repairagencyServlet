package com.example.repairagencyServlet.controller.constant;

public abstract class SqlQueries {

    public static String SELECT_CUSTOMER_BY_ID = "select app_user_id, email, amount_of_money from app_user where app_user_id=?;";
    public static String UPDATE_DEPOSIT_BY_USER_ID = "update app_user set amount_of_money=? where app_user_id=?;";
    public static String SELECT_REVIEWS_BY_MASTER_ID = "select review_id, review_description, u.email from review left join app_user u on review.master_id=u.app_user_id where u.app_user_id=?;";
    public static String SELECT_ORDER_BY_ID = "select o.id, o.area, o.offset_data_time, o.order_name, o.order_status, o.price, u.email from orders o left join app_user u on u.app_user_id=master_id where o.id=?;";
    public static String SET_PRICE = "update orders set price=?, order_status='WAIT_FOR_PAYMENT' where id=?;";
    public static String SET_MASTER = "update orders set master_id=? order_status='WAIT_FOR_MASTER_CONFIRMATION'where id=?;";
    public static String SELECT_ALL_ORDERS_BY_CUSTOMER_ID = "select o.id, o.area, o.offset_data_time, o.order_name, o.order_status, o.price, u.email from orders o left join app_user u on u.app_user_id=master_id where o.customer_id=?;";
    public static String PAYMENT_BY_ORDER_ID = "update orders set order_status='PAID' where id=?;";
    public static String SELECT_AMOUNT_OF_MONEY_BY_USER_ID = "select amount_of_money from app_user where app_user_id=?;";
    public static String SELECT_ALL_ORDERS_BY_MASTER_ID = "select o.id, o.area, o.offset_data_time, o.order_name, o.order_status, o.price, u.email from orders o left join app_user u on u.app_user_id=master_id where o.master_id=?;";
    public static String MARK_AS_IN_WORK = "update orders set order_status='IN_WORK' where id=?;";
    public static String MARK_AS_DONE = "update orders set order_status='DONE' where id=?; ";
}
