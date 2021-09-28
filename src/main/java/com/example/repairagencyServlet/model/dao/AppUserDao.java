package com.example.repairagencyServlet.model.dao;

import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.model.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserDao extends GenericDAO {
    List<AppUser> findAllMasters();

    List<AppUser> findAllCustomers() throws UserAlreadyExistAuthenticationException;

    Optional<AppUser> findById(Long id);

    AppUser findByEmail(String email, String password);
}
