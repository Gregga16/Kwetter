package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.KweetDAO;
import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KweetService {

    @Inject
    KweetDAO kweetDAO;

    @Inject
    UserDAO userDAO;

    public void addKweet(Kweet kweet) {
//        User user = userDAO.findById(id);
        kweetDAO.createKweet(kweet);
    }

    public List<Kweet> findAllKweets() {
        return kweetDAO.findAllKweets();
    }

}
