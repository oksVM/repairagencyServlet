package com.example.repairagencyServlet.model.dao.impl;

import com.example.repairagencyServlet.controller.config.PasswordConfig;
import com.example.repairagencyServlet.exception.UserAlreadyExistAuthenticationException;
import com.example.repairagencyServlet.model.dao.AppUserDao;
import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public int create(AppUser appUser) throws UserAlreadyExistAuthenticationException {
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
                  throw new UserAlreadyExistAuthenticationException();
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
    public AppUser findByEmail(String email) {

        try (PreparedStatement ps = connection.prepareStatement("select * from app_user where email = ?")) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            AppUser user = null;
            while (rs.next()) {
                user = new AppUser();
                user.setId(rs.getLong("app_user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword("password");
                user.setRole(Role.valueOf(rs.getString("role")));
            }

            if (user == null) {
                return null;
            }

            if (user.getEmail().equals(email)) {
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AppUser updateDeposit(Integer money, Long id) {
        AppUser appUser = new AppUser();

        try (PreparedStatement ps1 = connection.prepareStatement(
                "select amount_of_money from app_user where app_user_id = ?;")) {
            connection.setAutoCommit(false);
            ps1.setLong(1,id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Integer amountOfMoney = rs1.getInt("amount_of_money");
                try (PreparedStatement ps2 = connection.prepareStatement(
                        "update app_user set amount_of_money = ? where app_user_id = ?;")) {
                    ps2.setInt(1,amountOfMoney+money);
                    ps2.setLong(2,id);
                    ps2.executeUpdate();
                    connection.commit();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return appUser;
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
