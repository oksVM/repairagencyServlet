package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.controller.config.PasswordConfig;
import com.example.repairagencyServlet.model.dao.AppUserDao;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCAppUserDao implements AppUserDao {
    private Connection connection;

    public JDBCAppUserDao(Connection connection) {
        this.connection = connection;
    }

    public JDBCAppUserDao() {
    }

    public int create(AppUser appUser){
        String INSERT_APPUSER_SQL="INSERT INTO app_user (email, first_name, last_name, password, role) VALUES (?, ?, ?, ?,?)";
        int result = 0;

            try(PreparedStatement preparedStatement=connection.prepareStatement(INSERT_APPUSER_SQL)) {
                preparedStatement.setString(1, appUser.getEmail());
                preparedStatement.setString(2, appUser.getFirstName());
                preparedStatement.setString(3, appUser.getLastName());
                PasswordConfig passwordConfig = new PasswordConfig();
                preparedStatement.setString(4, passwordConfig.passwordEncoder().encode(appUser.getPassword()));
                preparedStatement.setString(5, "CUSTOMER");
                System.out.println(preparedStatement);
                result = preparedStatement.executeUpdate();
            } catch (SQLException e){

            }
            return result;
    }

    @Override
    public List<AppUser> findAllByRole(Role role) {
        return null;
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public Optional findById(long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void close() {

    }

    public int createMaster(AppUser appUser) {
        String INSERT_APPUSER_SQL="INSERT INTO app_user (email, first_name, last_name, password, role) VALUES (?, ?, ?, ?,?)";
        int result = 0;

        try(PreparedStatement preparedStatement=connection.prepareStatement(INSERT_APPUSER_SQL)) {
            preparedStatement.setString(1, appUser.getEmail());
            preparedStatement.setString(2, appUser.getFirstName());
            preparedStatement.setString(3, appUser.getLastName());
            PasswordConfig passwordConfig = new PasswordConfig();
            preparedStatement.setString(4, passwordConfig.passwordEncoder().encode(appUser.getPassword()));
            preparedStatement.setString(5, "MASTER");
            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e){

        }
        return result;
    }
}
