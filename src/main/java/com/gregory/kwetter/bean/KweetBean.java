package com.gregory.kwetter.bean;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KweetBean {
    @Inject
    KweetService kweetService;

    public void addKweet(Kweet kweet) {
        kweetService.addKweet(kweet);
    }

    public List<Kweet> findAllKweets() {
        return kweetService.findAllKweets();
    }
}
