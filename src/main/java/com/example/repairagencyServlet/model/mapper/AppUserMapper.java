package com.example.repairagencyServlet.model.mapper;

import com.example.repairagencyServlet.model.entity.AppUser;
import com.example.repairagencyServlet.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AppUserMapper implements ObjectMapper<AppUser> {

    @Override
    public AppUser extractFromResultSet(ResultSet rs) throws SQLException {
        return new AppUser.Builder()
                .id(rs.getLong("app_user_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .role(Role.valueOf(rs.getString("role")))
                .amountOfMoney(rs.getInt("amount_of_money"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .build();
    }

    @Override
    public AppUser makeUnique(Map<Long, AppUser> cache,
                           AppUser user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
