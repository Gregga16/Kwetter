package com.gregory.kwetter.bean;


import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserBean {

    @Inject
    UserService userService;

    public void createUser(User user) {
        userService.createUser(user);
    }

    public void getUser(int userid) { userService.getUser(userid); }

    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    public User findByName(String userName) {
        return userService.findByName(userName);
    }

    public User findById(Long id) {
        return userService.findById(id);
    }
}
