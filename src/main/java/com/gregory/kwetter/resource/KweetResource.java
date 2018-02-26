package com.gregory.kwetter.resource;

import com.gregory.kwetter.bean.KweetBean;
import com.gregory.kwetter.bean.UserBean;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Stateless
@Path("tweet")
@Produces(MediaType.APPLICATION_JSON)
public class KweetResource {

    @Inject
    private KweetBean kweetBean;

    @GET
    @Path("test")
    public String getKweet() {
        return "Kweet";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("allKweets")
    public List<Kweet> findAllKweets() {
        return kweetBean.findAllKweets();
    }

    @POST
    @Path("addKweet")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addKweet(Kweet kweet) {
        kweetBean.addKweet(kweet);
    }

}
