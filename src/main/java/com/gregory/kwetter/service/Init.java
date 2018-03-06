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
        User user1 = new User("gregory", "test");
        User user2 = new User("piet", "test123");
        User user3 = new User("frank", "abc534");
        User user4 = new User("hein", "geen");
        User user5 = new User("Pieter", "wachtwoord");
        user1.addKweet("Gregorys tweet");
        user1.addKweet("Dit is een nieuwe tweet");
        user2.addKweet("User 2 tweet");

        user1.follow(user2);
        user1.follow(user3);
        user1.follow(user4);
        user1.follow(user5);
        user2.follow(user1);
        user3.follow(user1);
        userDAO.createUser(user1);
        userDAO.createUser(user2);
        userDAO.createUser(user3);
        userDAO.createUser(user4);
        userDAO.createUser(user5);

    }
}
