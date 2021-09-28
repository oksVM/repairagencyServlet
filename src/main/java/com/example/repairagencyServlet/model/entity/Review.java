package com.example.repairagencyServlet.model.entity;


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

    public static class Builder {
        private final Review review;

        public Builder() {
            this.review = new Review();
        }

        public Builder id(long id) {
            review.id = id;
            return this;
        }

        public Builder description(String description) {
            review.reviewDescription = description;
            return this;
        }

        public Builder master(AppUser user) {
            review.master = user;
            return this;
        }

        public Review build() {
            return review;
        }
    }
}
