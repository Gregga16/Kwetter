package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    UserDAO userDAO;

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void getUser(int userid) { userDAO.getUser(userid); }

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    public User findByName(String userName) {
        return userDAO.findByName(userName);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }
}
