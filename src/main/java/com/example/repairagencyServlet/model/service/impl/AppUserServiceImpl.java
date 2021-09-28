package com.example.repairagencyServlet.model.service.impl;

import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.exception.UserNotFoundException;
import com.example.repairagencyServlet.model.dao.AppUserDao;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.dao.impl.JDBCAppUserDao;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.service.AppUserService;

import java.util.List;


public class AppUserServiceImpl implements AppUserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public AppUser saveNewCustomer(AppUser appUser) throws UserAlreadyExistAuthenticationException {
        try (JDBCAppUserDao dao = (JDBCAppUserDao) daoFactory.createAppUserDao()) {
            dao.create(appUser);
        }
        return appUser;
    }

    @Override
    public AppUser updateDeposit(Integer money, Long id) {
        try (JDBCAppUserDao dao = (JDBCAppUserDao) daoFactory.createAppUserDao()) {
            AppUser result = dao.updateDeposit(money, id);
            return result;
        }
    }

    @Override
    public AppUser saveNewMaster(AppUser appUser) throws UserAlreadyExistAuthenticationException {
        try (JDBCAppUserDao dao = (JDBCAppUserDao) daoFactory.createAppUserDao()) {
            dao.createMaster(appUser);
        }
        return appUser;
    }

    @Override
    public List<AppUser> findAllCustomers() {
        try (JDBCAppUserDao dao = (JDBCAppUserDao) daoFactory.createAppUserDao()) {
            List<AppUser> list = dao.findAllCustomers();
            return list;
        }
    }

    @Override
    public List<AppUser> findAllMasters() {
        try (JDBCAppUserDao dao = (JDBCAppUserDao) daoFactory.createAppUserDao()) {
            List<AppUser> list = dao.findAllMasters();
            return list;
        }
    }

    @Override
    public AppUser loadUserByEmail(String email, String password) throws UserNotFoundException {
        try (AppUserDao dao = daoFactory.createAppUserDao()) {
            return dao.findByEmail(email, password);
        }
    }

    @Override
    public AppUser findById(Long id) throws UserNotFoundException {
        try (AppUserDao dao = daoFactory.createAppUserDao()) {
            return dao.findById(id).orElseThrow(UserNotFoundException::new);
        }
    }
}





