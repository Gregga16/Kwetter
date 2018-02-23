package com.gregory.kwetter.resource;

import com.gregory.kwetter.bean.UserBean;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserBean userBean;

    @GET
    @Path("/{userid}")
    public String getUser() {
         return "User";
    }

    @POST
    @Path("addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
        userBean.addUser(user);
    }

}
