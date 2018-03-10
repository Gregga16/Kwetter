package com.gregory.kwetter.resource;

import com.gregory.kwetter.bean.KweetBean;
import com.gregory.kwetter.bean.UserBean;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.KweetService;
import com.gregory.kwetter.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import java.util.List;

@Stateless
@Path("kweet")
@Produces(MediaType.APPLICATION_JSON)
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @GET
    @Path("test")
    public String getKweet() {
        return "Kweet";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allKweets")
    public List<Kweet> findAllKweets() {
        return kweetService.findAllKweets();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addKweet(@FormParam("message") String message, @FormParam("userId") Long id) {
        User user = userService.findById(id);
        kweetService.addKweet(new Kweet(message, user));
    }

}
