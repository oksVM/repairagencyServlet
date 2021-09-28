package com.example.repairagencyServlet.model.service;

import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface AppUserService{

    AppUser saveNewCustomer(AppUser appUser) throws UserAlreadyExistAuthenticationException;
    AppUser findById(Long id) throws UserNotFoundException;
    AppUser updateDeposit(Integer money, Long id);
    AppUser saveNewMaster(AppUser appUser) throws UserAlreadyExistAuthenticationException;
    List<AppUser> findAllCustomers();
    List<AppUser> findAllMasters();
    AppUser loadUserByEmail(String email, String password) throws UsernameNotFoundException, UserNotFoundException;
}
