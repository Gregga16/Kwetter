package com.gregory.kwetter.bean;

import com.gregory.kwetter.model.User;
import com.gregory.kwetter.service.UserService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class AuthBean {

    private User user;

    private String username;
    private String password;
    private String originalURL;

    @Inject
    HttpServletRequest request;
    @Inject
    UserService userService;
    @Inject
    HttpSession session;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        session = request.getSession();

        try {
            request.login(username, password);

//            user = new User("none", username, "Gregory", "Lammers", "Helmond", "www.test.com", "24 jaar oud", "gregory_lammers@hotmail.com", password);

            user = userService.findByName(username);
            session.setAttribute("userID", user.getUserName());

            if(request.isUserInRole("Admin")) {
                externalContext.redirect(externalContext.getRequestContextPath() + "/admin/admin.xhtml");
            }

            if(request.isUserInRole("Kweeter")) {
                externalContext.redirect(externalContext.getRequestContextPath() + "/user/userPage.xhtml");
            }

        } catch (ServletException e) {
            e.printStackTrace();

            String messageText="Error while logging in.";
            FacesMessage fm = new FacesMessage(messageText);
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Login Information Incorrect."));
        }

        Logger.getGlobal().log(Level.SEVERE,"User: " + user.getUserName() + getPassword());
    }

    public void logout() throws IOException {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
