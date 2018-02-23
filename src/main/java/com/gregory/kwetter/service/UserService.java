package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    UserDAO userDAO;

    public void addUser(User user) {
        userDAO.createUser(user);
    }

    public void getUser(int userid) { userDAO.getUser(userid); }
}
