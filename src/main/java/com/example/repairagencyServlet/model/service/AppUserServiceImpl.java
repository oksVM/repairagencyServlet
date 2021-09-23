package com.example.repairagencyServlet.model.service;

import com.example.repairagencyServlet.controller.dto.DepositDTO;
import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.model.dao.AppUserDao;
import com.example.repairagencyServlet.model.dao.DaoFactory;
import com.example.repairagencyServlet.model.dao.impl.JDBCAppUserDao;
import com.example.repairagencyServlet.model.entity.AppUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;


public class AppUserServiceImpl implements AppUserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public AppUser saveNewCustomer(AppUser appUser) throws UserAlreadyExistAuthenticationException {
        try (JDBCAppUserDao dao = (JDBCAppUserDao) daoFactory.createAppUserDao()) {
            dao.create(appUser);
        }
        /*        if (appUserRepository.findByEmail(appUserRegistrationDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistAuthenticationException(appUserRegistrationDto.getEmail());
        }*/
        return appUser;
    }

    @Override
    public AppUser findById(Long id) throws UsernameNotFoundException{
        //return appUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(""));
        return null;
    }

    @Override
    public AppUser updateDeposit(DepositDTO money, Long id) {
/*        AppUser updatedAppUser = appUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(""));
        updatedAppUser.setAmountOfMoney(updatedAppUser.getAmountOfMoney()+money.getAmountOfMoney());
        appUserRepository.save(updatedAppUser);
        return updatedAppUser;*/
        return null;
    }

    @Override
    public AppUser saveNewMaster(AppUser appUser) throws UserAlreadyExistAuthenticationException {
        try (JDBCAppUserDao dao = (JDBCAppUserDao) daoFactory.createAppUserDao()) {
            dao.createMaster(appUser);
        }
        /*        if (appUserRepository.findByEmail(appUserRegistrationDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistAuthenticationException(appUserRegistrationDto.getEmail());
        }*/
        return appUser;
    }

    @Override
    public List<AppUser> findAllCustomers() {
        return null;
        //return appUserRepository.findAllByRole(Role.CUSTOMER, pageable);
    }

    @Override
    public List<AppUser> findAllMasters() {
        return null;
        //return appUserRepository.findAllByRole(Role.MASTER, pageable);
    }



    @Override
    public AppUser loadUserByEmail(String email) throws UsernameNotFoundException {
        try (AppUserDao dao = daoFactory.createAppUserDao()){
            return dao.findByEmail(email);
        }
    }
}





