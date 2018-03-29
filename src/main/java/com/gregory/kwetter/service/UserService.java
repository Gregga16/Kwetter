package com.gregory.kwetter.service;

import com.gregory.kwetter.dao.UserDAO;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.Role;
import com.gregory.kwetter.model.User;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Named
@DeclareRoles({"Admin", "Kweeter"})
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Inject
    UserDAO userDAO;

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void editUser(User user) { userDAO.editUser(user); }

    public void editRole(Role role) { userDAO.editRole(role); }

    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    public User findByName(String userName) {
        return userDAO.findByName(userName);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public List<Kweet> findAllKweets(Long id) {
        return userDAO.findAllKweets(id);
    }

    public void addFollow(User user, User follower) {
        userDAO.addFollow(user, follower);
    }

    public Set<User> getFollowers(Long id) {
        return userDAO.getFollowers(id);
    }

    public Set<User> getFollowing(Long id) {
        return userDAO.getFollowing(id);
    }

    @RolesAllowed("Kweeter")
    public List<Kweet> getTimeLine(Long id) { return userDAO.getTimeLine(id); }

    public Role getRole(String roleID) {
        return userDAO.getRole(roleID);
    }

    public List<Role> getAllRoleGroups(){
        return userDAO.getAllRoleGroups();
    }

}
