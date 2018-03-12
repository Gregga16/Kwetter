package com.gregory.kwetter.dao;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Stateless
public class KweetDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public void addKweet(Kweet kweet) {
        entityManager.persist(kweet);
    }

    public List<Kweet> findAllKweets() {
        Query q = entityManager.createNamedQuery("Kweet.findAllKweets");
        return q.getResultList();
    }

    public Kweet findById(Long id) {
        Query q = entityManager.createNamedQuery("Kweet.findById");
        q.setParameter("id", id);
        return (Kweet) q.getSingleResult();
    }

    public Set<User> getMetions(Long id) {
        Kweet kweet = findById(id);
        return kweet.getMentions();
    }

    public Set<User> getLikes(Long id) {
        Kweet kweet = findById(id);
        return kweet.getLikes();
    }
}
