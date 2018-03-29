package com.gregory.kwetter.model;

import com.gregory.kwetter.interceptor.KweetLoggedInterceptor;

import javax.interceptor.Interceptors;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAllUsers", query = "SELECT user FROM User user"),
        @NamedQuery(name = "User.findById", query = "SELECT user FROM User user WHERE user.id =:id"),
        @NamedQuery(name = "User.findByName", query = "SELECT user FROM User user WHERE user.userName =:name"),
        @NamedQuery(name = "User.findAllKweets", query = "select kweet from User user join user.kweets kweet where user.id =:id")
})
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String picture;
    @Column(unique = true)
    private String userName;
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

    @ManyToMany
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "userName", referencedColumnName = "userName"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleID"))
    private Set<Role> roles = new HashSet<>();

    @JsonbTransient
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> following = new HashSet<>();

    @JsonbTransient
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    public User() {}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String picture, String userName, String firstName, String lastName, String location, String web, String bio, String email, String password) {
        this.picture = picture;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.web = web;
        this.bio = bio;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserName() { return userName; }

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
        return Collections.unmodifiableSet(kweets);
    }

    public Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles;
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

    public void setUserName(String userName) { this.userName = userName; }

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

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Kweet addKweet(String message, User user) {
        Kweet kweet = new Kweet(message, user);
        addKweet(kweet);
        return kweet;
    }

    @Interceptors(KweetLoggedInterceptor.class)
    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public void removeKweet(Kweet kweet) {
        Iterator<Kweet> iter = kweets.iterator();
        while (iter.hasNext()) {
            Kweet kwt = iter.next();
            if (kwt.getUser().equals(kweet.getUser())) {
                if (kwt.getMessage().equals(kweet.getMessage())) {
                    iter.remove();
                }
            }
        }
    }

    public Kweet findKweet(Long id) {
        Iterator<Kweet> iter = kweets.iterator();
        while (iter.hasNext()) {
            Kweet kwt = iter.next();
            if (kwt.getId().equals(id)) {
                return kwt;
            }
        }
        return null;
    }

    public boolean follow(User user) {
        if (user == null || user == this) {
            return false;
        }

        user.addFollower(this);

        return following.add(user);
    }

    public boolean unfollow(User user) {
        user.removeFollower(this);
        return following.remove(user);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    private void addFollower(User follower) {
        this.followers.add(follower);
    }

    private boolean removeFollower(User user) { return followers.remove(user); }
}
