package com.gregory.kwetter.dao;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.Role;
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

    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public void editUser(User user) { entityManager.merge(user); }

    public void editRole(Role role) { entityManager.merge(role); }

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

    public List<Kweet> getTimeLine(Long id) {
        String sql = "SELECT k.*  FROM Kweet k, USER_USER u WHERE (u.`following_ID` = k.`User_id` AND u.`followers_ID` = " + id + ") OR k.`User_id` = " + id + " GROUP BY k.`ID` ORDER BY k.`EVENTDATE` DESC LIMIT 10";

        Query q = entityManager.createNativeQuery(sql, Kweet.class);
        List<Kweet> kweet = q.getResultList();
        System.out.println(kweet.size());
        return kweet;
    }

    public Role getRole(String roleID) {
        Query q = entityManager.createNamedQuery("RoleGroup.getByRoleID");
        q.setParameter("roleID", roleID);
        return (Role) q.getSingleResult();
    }

    public List<Role> getAllRoleGroups() {
        List<Role> gebruikerGroups = entityManager.createNamedQuery("RoleGroup.getAll").getResultList();
        return gebruikerGroups;
    }


}
