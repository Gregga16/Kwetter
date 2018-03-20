package com.gregory.kwetter.bean;


import com.gregory.kwetter.model.Kweet;
import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.KweetService;
import com.gregory.kwetter.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    HttpServletRequest request;

    @Inject
    UserService userService;

    @Inject
    KweetService kweetService;

    @Inject
    HttpSession session;


    private User user;
    private Kweet kweet;

    private String kweetString = "";

    private List<Kweet> kweets;

    private List<Kweet> kwets;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        session = request.getSession();

        user = userService.findByName(getUserPrincipalName());

//        kweets = userService.findAllKweets(user.getId());

        kweets = userService.getTimeLine(user.getId());
    }

    private String getUserPrincipalName() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Principal principal = fc.getExternalContext().getUserPrincipal();
        if (principal == null) {
            return null;
        }
        return principal.getName();
    }

    public void logout() {
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error Signout -" + ex.getMessage());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }

    public String getKweetString() {
        return kweetString;
    }

    public void setKweetString(String kweetString) {
        this.kweetString = kweetString;
    }

    public List<Kweet> getKwets() {
        return kwets;
    }

    public void setKwets(List<Kweet> kwets) {
        this.kwets = kwets;
    }

    public List<Kweet> getKweets() {
        kweets.sort((o1, o2) -> o2.getEventDate().compareTo(o1.getEventDate()));
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
        Collections.sort(kweets, new Comparator<Kweet>() {
            public int compare(Kweet o1, Kweet o2) {
                return o1.getEventDate().compareTo(o2.getEventDate());
            }
        });
    }

    public void searchKweet(AjaxBehaviorEvent event) {
        UIInput input = (UIInput) event.getSource();
        String searchString = (String) input.getValue();
        this.setKweets((List<Kweet>) kweetService.findKweetOnText(searchString));
    }

    public void createKweet(AjaxBehaviorEvent event) {
        Kweet kweet = new Kweet(getKweetString(), getUser());
        kweetService.addKweet(kweet);
        this.kweets.add(kweet);

        kweets.sort((o1, o2) -> o2.getEventDate().compareTo(o1.getEventDate()));
    }
}
