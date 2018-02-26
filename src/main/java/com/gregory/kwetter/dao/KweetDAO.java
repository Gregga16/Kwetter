package com.gregory.kwetter.dao;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class KweetDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void createKweet(Kweet kweet) {
        entityManager.persist(kweet);
    }

    public List<Kweet> findAllKweets() {
        Query q = entityManager.createNamedQuery("Kweet.findAllKweets");
        return q.getResultList();
    }
}
