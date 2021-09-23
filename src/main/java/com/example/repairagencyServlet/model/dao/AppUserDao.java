package com.example.repairagencyServlet.model.dao;

import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface AppUserDao extends GenericDAO{
    List<AppUser> findAllMasters();
    List<AppUser> findAllCustomers();
    Optional<AppUser> findById(Long id);
    Optional <AppUser> findByEmail(String email);
}
