package com.gregory.kwetter.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAllUsers", query = "SELECT user FROM User user"),
        @NamedQuery(name = "User.findById", query = "SELECT user FROM User user WHERE user.id =:id"),
        @NamedQuery(name = "User.findByName", query = "SELECT user FROM User user WHERE user.firstName =:name"),
        @NamedQuery(name = "User.findAllKweets", query = "select kweet from User user join user.kweets kweet where user.id =:id")
})
public class User implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String picture;
    @Column(unique = true)
    private String firstName;
    private String lastName;
    private String location;
    private String web;
    private String bio;
    @Column(unique = true)
    private String email;
    private String password;

    @JsonbTransient
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Kweet> kweets = new HashSet<>();

    @JsonbTransient
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> following = new HashSet<>();

    @JsonbTransient
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() {
        return location;
    }

    public String getWeb() {
        return web;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Kweet> getKweets() {
        return kweets;
    }

    public Set<User> getFollowing() {
        if (following == null) {
            following = new HashSet<>();
        }
        return following;
    }

    public Set<User> getFollowers() {
        if (followers == null) {
            followers = new HashSet<>();
        }
        return followers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKweets(Set<Kweet> kweets) {
        this.kweets = kweets;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Kweet addKweet(String message) {
        Kweet kweet = new Kweet(message, this);
        this.kweets.add(kweet);
        return kweet;
    }
//
    public boolean follow(User user) {
        if (user == null || user == this) {
            return false;
        }

        user.addFollower(this);

        return following.add(user);
    }

    private void addFollower(User follower) {
        this.followers.add(follower);
    }

}
