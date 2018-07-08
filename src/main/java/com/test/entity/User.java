package com.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String email;

    @JsonIgnore
    private String password;

    private Double salary;

    private String typeOfUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTypeOfUser() { return typeOfUser; }

    public void setTypeOfUser(String typeOfUser) { this.typeOfUser = typeOfUser; }

    public User(long id, String email, String password, Double salary, String typeOfUser) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.typeOfUser = typeOfUser;
    }

    public User() {}
}
