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
            return result;}

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
   /* public List<AppUser> store = new ArrayList<>();

    public AppUser getById(int id){
        AppUser appUserResult = new AppUser();
        for(AppUser appUser:store){
            if(appUser.getId()==id){
                appUserResult=appUser;
            }
        }
        return appUserResult;
    }

    public AppUser getAppUserByLoginPassword(final String email, final String password){
        AppUser appUserResult = new AppUser();
        appUserResult.setId(-1L);

        for (AppUser appUser:store){
            if (appUser.getEmail().equals(email)&&appUser.getPassword().equals(password)){
                appUserResult=appUser;
            }
        }
        return appUserResult;
    }

    public boolean add(final AppUser appUser){
        for (AppUser appU: store){
            if(appU.getEmail().equals(appUser.getEmail())&&appU.getPassword().equals(appUser.getPassword())){
                return false;
            }
        }
        return store.add(appUser);
    }

    public Role getRoleByLoginPassword(final String email, final String password) {
        Role result = Role.UNKNOWN;
        for (AppUser appU: store){
            if(appU.getEmail().equals(email)&&appU.getPassword().equals(password)){
                result=appU.getRole();
            }
        }
        return result;
    }

    public boolean userIsExist(final String email, final String password){
        boolean result = false;
        for(AppUser appU:store){
            if(appU.getEmail().equals(email)&&appU.getPassword().equals(password)){
                result=true;
                break;
            }
        }
        return result;
    }*/

}
