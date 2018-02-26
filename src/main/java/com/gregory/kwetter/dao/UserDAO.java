package com.gregory.kwetter.dao;

import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public void getUser(int userid) { entityManager.find(User.class, userid); }

    public List<User> findAllUsers() {
        Query q = entityManager.createNamedQuery("User.findAllUsers");
        return q.getResultList();
    }

    public User findByName(String userName) {
        Query q = entityManager.createNamedQuery("User.findByName");
        q.setParameter("name", userName);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public User findById(Long id) {
        Query q = entityManager.createNamedQuery("User.findById");
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }
}
