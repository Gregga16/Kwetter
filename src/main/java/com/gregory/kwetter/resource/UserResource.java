package com.gregory.kwetter.resource;

import com.gregory.kwetter.bean.UserBean;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Stateless
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("test")
    public String getUser() {
         return "User";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("username/{userName}")
    public List<User> findByName(@PathParam("userName") String userName) {
        return userService.findByName(userName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("userid/{userId}")
    public User findById(@PathParam("userId") Long id) {
        return userService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allKweets/{userId}")
    public List<Kweet> findAllKweets(@PathParam("userId") Long id) {
        return userService.findAllKweets(id);
    }

    @POST
    @Path("createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User user) {
        userService.createUser(user);
    }

    @GET
    @Path("{userId}/followers")
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<User> getFollowers(@PathParam("userId") Long id) {
        return userService.getFollowers(id);
    }

    @GET
    @Path("{userId}/following")
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<User> getFollowing(@PathParam("userId") Long id) {
        return userService.getFollowing(id);
    }

    @GET
    @Path("{userId}/timeline")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Kweet> getTimeLine(@PathParam("userId") Long id) {
        return userService.getTimeLine(id);
    }


}
