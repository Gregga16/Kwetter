package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.KweetDAO;
import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.Role;
import com.gregory.kwetter.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class Init {

    @Inject
    UserDAO userDAO;

    @PersistenceContext(unitName = "Kwetter")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        Role admin = new Role("Admin");
        Role kweeter = new Role("Kweeter");

        User user1 = new User("none", "Gregga", "Gregory", "Lammers", "Helmond", "www.test.com", "24 jaar oud", "gregory_lammers@hotmail.com", "test");
        User user2 = new User("none", "Pietje", "pietje", "jansen", "Helmond", "www.test.com", "22 jaar oud", "piet@hotmail.com", "test");
        User user3 = new User("none", "Henkie", "henkie", "fristen", "Helmond", "www.test.com", "21 jaar oud", "henk@hotmail.com", "test");
        User user4 = new User("none", "Admin", "Admin", "Admin", "Helmond", "www.test.com", "21 jaar oud", "admin@hotmail.com", "test");
//        User user4 = new User("hein_vermeulen@hotmail.com", "geen");
//        User user5 = new User("pieter_vd_kimmenade@hotmail.com", "wachtwoord");

        entityManager.persist(admin);
        entityManager.persist(kweeter);

        user1.addRole(admin);
        user2.addRole(kweeter);
        user3.addRole(kweeter);
        user4.addRole(admin);

        Kweet user1kweet1 = new Kweet("Gregorys fuck kut tweet", user1);
        user1.addKweet(user1kweet1);
        Kweet user1kweet2 = new Kweet("Dit is een nieuwe tweet", user1);
        user1.addKweet(user1kweet2);
        user1kweet1.addMention(user2);
        user1kweet1.addMention(user3);
        user1kweet1.like(user2);
        user1kweet1.like(user3);
//        user1kweet1.like(user4);
        user1kweet2.like(user2);
        Kweet user2kweet1 = new Kweet("User 2 makes a tweet", user2);
        user2.addKweet(user2kweet1);
        Kweet user3kweet1 = new Kweet("User 3 makes a tweet", user3);
        user3.addKweet(user3kweet1);
//        Kweet user4kweet1 = user4.addKweet("User 4 makes a tweet");
//        Kweet user5kweet1 = user5.addKweet("User 5 makes a tweet");
//        Kweet user5kweet2 = user5.addKweet("User 5 makes a another tweet");

        user1.follow(user2);
        user1.follow(user3);
//        user1.follow(user4);
//        user1.follow(user5);
        user2.follow(user1);
        user3.follow(user1);
        userDAO.createUser(user1);
        userDAO.createUser(user2);
        userDAO.createUser(user3);
        userDAO.createUser(user4);
//        userDAO.createUser(user5);

    }
}
