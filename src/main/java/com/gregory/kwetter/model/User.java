package com.gregory.kwetter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAllUsers", query = "SELECT user FROM User user"),
        @NamedQuery(name = "User.findById", query = "SELECT user FROM User user WHERE user.id =:id"),
        @NamedQuery(name = "User.findByName", query = "SELECT user FROM User user WHERE user.firstName =:name")
})
public class User implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String picture;
    private String firstName;
    private String lastName;
    private String location;
    private String web;
    private String bio;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Kweet> kweets = new ArrayList<>();

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
