package com.example.repairagencyServlet.model.entity;


import com.example.repairagencyServlet.model.entity.AppUser;

public class Review {

    private Long id;
    private String reviewDescription;
    private AppUser master;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public AppUser getMaster() {
        return master;
    }

    public void setMaster(AppUser master) {
        this.master = master;
    }
}
