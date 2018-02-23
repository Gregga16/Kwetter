package com.gregory.kwetter.bean;


import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserBean {

    @Inject
    UserService userService;

    public void addUser(User user) {
        userService.addUser(user);
    }

    public void getUser(int userid) { userService.getUser(userid); }
}
