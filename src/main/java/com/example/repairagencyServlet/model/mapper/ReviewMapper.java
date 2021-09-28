package com.example.repairagencyServlet.model.mapper;

import com.example.repairagencyServlet.model.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReviewMapper implements ObjectMapper<Review>{
    @Override
    public Review extractFromResultSet(ResultSet rs) throws SQLException {
        return new Review.Builder()
                .id(rs.getLong("review_is"))
                .description(rs.getString("review_description"))
                .build();
    }

    @Override
    public Review makeUnique(Map<Long, Review> cache, Review entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
