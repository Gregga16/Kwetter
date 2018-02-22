package com.gregory.kwetter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQuery(name= "user.All", query = "SELECT u FROM User u")
public class User implements Serializable{

    @Id
    @GeneratedValue
    private int id;

    private String picture;
    private String firstName;
    private String lastName;
    private String location;
    private String web;
    private String bio;
    private String email;
    private String password;

    public User() {}

    public User(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}
