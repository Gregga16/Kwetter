package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.KweetDAO;
import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class Init {

    @Inject
    UserDAO userDAO;


    @PostConstruct
    public void init() {
//        User user1 = new User("gregory_lammers@hotmail.com", "test");
        User user1 = new User("none", "Gregga", "Gregory", "Lammers", "Helmond", "www.test.com", "24 jaar oud", "gregory_lammers@hotmail.com", "test");

        User user2 = new User("piet@hotmail.com", "test123");
        User user3 = new User("frankhaverstok@hotmail.com", "abc534");
        User user4 = new User("hein_vermeulen@hotmail.com", "geen");
        User user5 = new User("pieter_vd_kimmenade@hotmail.com", "wachtwoord");

//        Kweet kweet1 = user1.addKweet("Gregorys tweet");
//        user1.addKweet("Gregorys tweet");
//        user1.addKweet("Dit is een nieuwe tweet");
//        user2.addKweet("User 2 makes a tweet");
//        user2.addKweet("User 2 makes another tweet");
//        user3.addKweet("User 3 makes a tweet");
//        user4.addKweet("User 4 makes a tweet");
//
//        user1.follow(user2);
//        user1.follow(user3);
//        user1.follow(user4);
//        user1.follow(user5);
//        user2.follow(user1);
//        user3.follow(user1);
//        userDAO.createUser(user1);
//        userDAO.createUser(user2);
//        userDAO.createUser(user3);
//        userDAO.createUser(user4);
//        userDAO.createUser(user5);

    }
}
