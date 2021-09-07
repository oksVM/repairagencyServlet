package com.example.repairagencyServlet.model.dao;

import com.example.repairagencyServlet.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;
    public abstract AppUserDao createAppUserDao();
    public abstract OrderDao createOrderDao();
    public abstract ReviewDao createReviewDao();


    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
