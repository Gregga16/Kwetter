package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Stateless
public class UserService {

    @Inject
    UserDAO userDAO;

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    public User findByName(String userName) {
        return userDAO.findByName(userName);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public List<Kweet> findAllKweets(Long id) {
        return userDAO.findAllKweets(id);
    }

    public void addFollow(User user, User follower) {
        userDAO.addFollow(user, follower);
    }

    public Set<User> getFollowers(Long id) {
        return userDAO.getFollowers(id);
    }

    public Set<User> getFollowing(Long id) {
        return userDAO.getFollowing(id);
    }
}
