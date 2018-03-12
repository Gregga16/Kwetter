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
        User user1 = new User("none", "Gregga", "Gregory", "Lammers", "Helmond", "www.test.com", "24 jaar oud", "gregory_lammers@hotmail.com", "test");
        User user2 = new User("none", "Pietje", "pietje", "jansen", "Helmond", "www.test.com", "22 jaar oud", "piet@hotmail.com", "test");
        User user3 = new User("none", "Henkie", "henkie", "fristen", "Helmond", "www.test.com", "21 jaar oud", "henk@hotmail.com", "test");

        User user4 = new User("hein_vermeulen@hotmail.com", "geen");
        User user5 = new User("pieter_vd_kimmenade@hotmail.com", "wachtwoord");

        Kweet user1kweet1 = user1.addKweet("Gregorys fuck kut tweet");
        Kweet user1kweet2 = user1.addKweet("Dit is een nieuwe tweet");
        user1kweet1.addMention(user2);
        user1kweet1.addMention(user3);
        user1kweet1.like(user2);
        user1kweet1.like(user3);
        user1kweet1.like(user4);
        user1kweet2.like(user2);
        Kweet user2kweet1 = user2.addKweet("User 2 makes a tweet");
        Kweet user3kweet1 = user3.addKweet("User 3 makes a tweet");

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
