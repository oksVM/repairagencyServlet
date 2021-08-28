package com.example.repairagencyServlet.dao;

import com.example.repairagencyServlet.PasswordConfig;
import com.example.repairagencyServlet.model.AppUser;
import com.example.repairagencyServlet.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.registry.infomodel.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppUserDAO {

    public int registerAppUser(AppUser appUser){
        String INSERT_APPUSER_SQL="INSERT INTO app_user (email, first_name, last_name, password, role) VALUES (?, ?, ?, ?,?)";
        int result = 0;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/agencydb",
                "admindb", "2021")){
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_APPUSER_SQL);
            preparedStatement.setString(1,appUser.getEmail());
            preparedStatement.setString(2,appUser.getFirstName());
            preparedStatement.setString(3,appUser.getLastName());
            PasswordConfig passwordConfig=new PasswordConfig();
            preparedStatement.setString(4,passwordConfig.passwordEncoder().encode(appUser.getPassword()));
            preparedStatement.setString(5,"CUSTOMER");
            System.out.println(preparedStatement);
            result=preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
sqlException.printStackTrace();
        }
return result;}
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
