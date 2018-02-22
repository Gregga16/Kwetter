package com.gregory.kwetter.resource;

import com.gregory.kwetter.bean.UserBean;
import com.gregory.kwetter.model.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserBean userBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
        userBean.addUser(user);
    }

}
