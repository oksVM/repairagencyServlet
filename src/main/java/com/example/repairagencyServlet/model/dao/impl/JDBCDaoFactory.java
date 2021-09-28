package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.model.dao.AppUserDao;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.dao.OrderDao;
import com.example.repairagencyServlet.model.dao.ReviewDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public AppUserDao createAppUserDao() {
        return new JDBCAppUserDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public ReviewDao createReviewDao() {
        return new JDBCReviewDao(getConnection());
    }


    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
