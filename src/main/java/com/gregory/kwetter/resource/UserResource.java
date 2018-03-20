package com.gregory.kwetter.resource;

import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.UserService;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

//@Stateless
@RequestScoped
@Path("users")
@DeclareRoles({"Admin", "Kweeter"})
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    @Path("allUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    @Path("{userName}")
    public User findByName(@PathParam("userName") String userName) {
        return userService.findByName(userName);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    @Path("userid/{userId}")
    public User findById(@PathParam("userId") Long id) {
        return userService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    @Path("allKweets/{userId}")
    public List<Kweet> findAllKweets(@PathParam("userId") Long id) {
        return userService.findAllKweets(id);
    }

    @POST
    @Path("createUser")
    @RolesAllowed("Admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User user) {
        userService.createUser(user);
    }

    @GET
    @Path("{userId}/followers")
    @RolesAllowed("Admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<User> getFollowers(@PathParam("userId") Long id) {
        return userService.getFollowers(id);
    }

    @GET
    @Path("{userId}/following")
    @RolesAllowed("Admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<User> getFollowing(@PathParam("userId") Long id) {
        return userService.getFollowing(id);
    }

    @GET
    @Path("{userId}/timeline")
    @RolesAllowed("Admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Kweet> getTimeLine(@PathParam("userId") Long id) {
        return userService.getTimeLine(id);
    }


}
