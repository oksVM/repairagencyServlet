package com.example.repairagencyServlet.model.service;

import com.example.repairagencyServlet.controller.dto.AppUserRegistrationDto;
import com.example.repairagencyServlet.controller.dto.DepositDTO;
import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService extends UserDetailsService {

    AppUser saveNewCustomer(AppUser appUser) throws UserAlreadyExistAuthenticationException;
    AppUser findById(Long id);
    AppUser updateDeposit(DepositDTO money, Long id);
    AppUser saveNewMaster(AppUserRegistrationDto appUserRegistrationDto) throws UserAlreadyExistAuthenticationException;
    List<AppUser> findAllCustomers();
    List<AppUser> findAllMasters();
}
