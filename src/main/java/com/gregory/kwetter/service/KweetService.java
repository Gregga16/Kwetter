package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.KweetDAO;
import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.interceptor.KweetLoggedInterceptor;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class KweetService {

    @Inject
    KweetDAO kweetDAO;

    @Inject
    UserDAO userDAO;

    @Interceptors(KweetLoggedInterceptor.class)
    public void addKweet(Kweet kweet) {
        for (User u : findMentions(kweet.getMessage())) {
            kweet.addMention(u);
        }
        kweetDAO.addKweet(kweet);
    }

    public List<Kweet> findAllKweets() {
        return kweetDAO.findAllKweets();
    }

    public Set<User> getMentions(Long id) {
        return kweetDAO.getMetions(id);
    }

    public Set<User> getLikes(Long id) {
        return kweetDAO.getLikes(id);
    }

    private List<User> findMentions(String message) {
        List<User> mentions = new ArrayList<>();
        String prefix = " ".concat(message);
        Matcher m = Pattern.compile("(?:\\@)([A-Za-z0-9_]+)").matcher(prefix);

        while (m.find()) {
            List<User> users = userDAO.findByName(m.group(1));
            if (!users.isEmpty()) {
                System.out.print(users.get(0).getFirstName());
                mentions.add(users.get(0));
            }
        }
        return mentions;
    }
}
