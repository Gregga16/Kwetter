package com.gregory.kwetter.resource;

import com.gregory.kwetter.bean.UserBean;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserBean userBean;

    @GET
    @Path("test")
    public String getUser() {
         return "User";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allUsers")
    public List<User> findAllUsers() {
        return userBean.findAllUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("username/{userName}")
    public User findByName(@PathParam("userName") String userName) {
        return userBean.findByName(userName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("userid/{userId}")
    public User findById(@PathParam("userId") Long id) {
        return userBean.findById(id);
    }

//    @POST
//    @Path("createUser/{firstName}/{email}/{password}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void createUser(@PathParam("firstName") String firstName, @PathParam("email") String email, @PathParam("password") String password) {
//        User user = new User(firstName, email, password);
//        userBean.createUser(user);
//    }

    @POST
    @Path("createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User user) {
        userBean.createUser(user);
    }

}
