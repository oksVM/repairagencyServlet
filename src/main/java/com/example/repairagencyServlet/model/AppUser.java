package com.example.repairagencyServlet.model;


import javax.persistence.*;
import java.util.Set;
 public class AppUser {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private Integer amountOfMoney;
    private Area professionalArea;
    private Double score;
    private Set<Order> customerOrders;
    private Set<Order> masterOrders;
    private Set<Review> reviews;

     public AppUser() {
     }

     public AppUser(String firstName, String lastName, String email, String password, Role role) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.role = role;
   }

    public Long getId() {
       return id;
    }

    public void setId(Long id) {
       this.id = id;
    }

    public String getFirstName() {
       return firstName;
    }

    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }

    public String getLastName() {
       return lastName;
    }

    public void setLastName(String lastName) {
       this.lastName = lastName;
    }

    public String getEmail() {
       return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    public String getPassword() {
       return password;
    }

    public void setPassword(String password) {
       this.password = password;
    }

    public Role getRole() {
       return role;
    }

    public void setRole(Role role) {
       this.role = role;
    }

    public Integer getAmountOfMoney() {
       return amountOfMoney;
    }

    public void setAmountOfMoney(Integer amountOfMoney) {
       this.amountOfMoney = amountOfMoney;
    }

    public Area getProfessionalArea() {
       return professionalArea;
    }

    public void setProfessionalArea(Area professionalArea) {
       this.professionalArea = professionalArea;
    }

    public Double getScore() {
       return score;
    }

    public void setScore(Double score) {
       this.score = score;
    }

    public Set<Order> getCustomerOrders() {
       return customerOrders;
    }

    public void setCustomerOrders(Set<Order> customerOrders) {
       this.customerOrders = customerOrders;
    }

    public Set<Order> getMasterOrders() {
       return masterOrders;
    }

    public void setMasterOrders(Set<Order> masterOrders) {
       this.masterOrders = masterOrders;
    }

    public Set<Review> getReviews() {
       return reviews;
    }

    public void setReviews(Set<Review> reviews) {
       this.reviews = reviews;
    }
 }


