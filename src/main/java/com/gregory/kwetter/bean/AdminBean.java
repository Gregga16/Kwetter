package com.gregory.kwetter.bean;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.Role;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.KweetService;
import com.gregory.kwetter.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminBean implements Serializable {

    @Inject
    UserService userService;

    @Inject
    KweetService kweetService;

    private List<User> users;
    private User selectedUser;

    private List<Kweet> kweets;
    private Kweet selectedKweet;

    private List<Role> roles;
    private String selectedRole;

    public AdminBean() {
    }

    @PostConstruct
    public void init() {
        kweets = kweetService.findAllKweets();
        roles = userService.getAllRoleGroups();
    }

    public List<User> getUsers() {
        users = userService.findAllUsers();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
    }

    public Kweet getSelectedKweet() {
        return selectedKweet;
    }

    public void setSelectedKweet(Kweet selectedKweet) {
        this.selectedKweet = selectedKweet;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public void promoteUser() {
        if(selectedUser != null && selectedRole != null) {
            Role rolegroup = userService.getRole(selectedRole);
            selectedUser.addRole(rolegroup);

            userService.editRole(rolegroup);
            userService.editUser(selectedUser);

            userService.getAllRoleGroups();

        }
    }

    public void demoteUser() {
        if(selectedUser != null && selectedRole != null) {
            Role rolegroup = userService.getRole(selectedRole);
            selectedUser.removeRole(rolegroup);

            userService.editRole(rolegroup);
            userService.editUser(selectedUser);

            userService.getAllRoleGroups();

        }
    }

    public void removeKweet() {
        if(selectedKweet != null){
            Kweet managedKweet = kweetService.findKweetById(selectedKweet.getId());
            kweetService.removeKweet(managedKweet);

            kweets = kweetService.findAllKweets();
        }
    }
}
