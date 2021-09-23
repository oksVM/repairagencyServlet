package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.controller.config.PasswordConfig;
import com.example.repairagencyServlet.model.dao.AppUserDao;
import com.example.repairagencyServlet.model.entity.*;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
    public List<AppUser> findAllMasters() {
        String SELECT_ALL_MASTERS="select app_user_id, first_name, last_name from app_user where role='MASTER'";
        List<AppUser> result = new ArrayList<>();

        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_MASTERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                AppUser user = new AppUser();
                user.setId(rs.getLong("app_user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                result.add(user);
            }
        } catch (SQLException e){

        }
        return result;
    }

    @Override
    public List<AppUser> findAllCustomers() {
        String SELECT_ALL_MASTERS="select app_user_id, first_name, last_name from app_user where role='CUSTOMER'";
        List<AppUser> result = new ArrayList<>();

        try(PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_MASTERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                AppUser user = new AppUser();
                user.setId(rs.getLong("app_user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                result.add(user);
            }
        } catch (SQLException e){

        }
        return result;
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return Optional.empty();
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
}
