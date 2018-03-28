package com.gregory.kwetter.resource;

import com.gregory.kwetter.interceptor.KweetLoggedInterceptor;
import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.KweetService;
import com.gregory.kwetter.service.UserService;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@RequestScoped
@DeclareRoles({"Admin", "Kweeter"})
@Path("kweet")
@Produces(MediaType.APPLICATION_JSON)
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    @Path("allKweets")
    public List<Kweet> findAllKweets() {
        return kweetService.findAllKweets();
    }

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addKweet(@FormParam("message") String message, @FormParam("userId") Long id) {
        User user = userService.findById(id);
        kweetService.addKweet(new Kweet(message, user));
    }

    @GET
    @RolesAllowed("Admin")
    @Path("{kweetId}/mentions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<User> getMentions(@PathParam("kweetId") Long id) {
        return kweetService.getMentions(id);
    }

    @GET
    @RolesAllowed("Admin")
    @Path("{kweetId}/likes")
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<User> getLikes(@PathParam("kweetId") Long id) {
        return kweetService.getLikes(id);
    }

    @GET
    @RolesAllowed("Admin")
    @Path("{text}/search")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Kweet> getKweetOnText(@PathParam("text") String text) {
        return kweetService.findKweetOnText(text);
    }

    @GET
    @Path("remove/{kweetid}")
    @RolesAllowed("Admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeKweet(@PathParam("kweetid") Long id) {
        Kweet kweet = kweetService.findKweetById(id);
        kweetService.removeKweet(kweet);
    }
}
