package com.gregory.kwetter.dao;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public void editUser(User user) { entityManager.merge(user); }

    public List<User> findAllUsers() {
        Query q = entityManager.createNamedQuery("User.findAllUsers");
        return q.getResultList();
    }

    public List<User> findByName(String userName) {
        Query q = entityManager.createNamedQuery("User.findByName");
        q.setParameter("name", userName);
        try {
            return q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public User findById(Long id) {
        Query q = entityManager.createNamedQuery("User.findById");
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }

    public List<Kweet> findAllKweets(Long id) {
        Query q = entityManager.createNamedQuery("User.findAllKweets");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public void addFollow(User user, User follower) {
        user = entityManager.find(User.class, user.getId());
        follower = entityManager.find(User.class, follower.getId());

        user.follow(follower);
    }

    public Set<User> getFollowers(Long id) {
        User user = findById(id);
        return user.getFollowers();
    }

    public Set<User> getFollowing(Long id) {
        User user = findById(id);
        return user.getFollowing();
    }
}
