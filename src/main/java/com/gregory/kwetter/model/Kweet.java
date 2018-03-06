package com.gregory.kwetter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery(name = "Kweet.findAllKweets", query = "SELECT kweet FROM Kweet kweet")
public class Kweet implements Serializable{

    @Id
    @GeneratedValue
    private int Id;

    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "User_id")
    private User user;

    public Kweet() {}

    public Kweet(String message, User user) {
        this.message = message;
        this.eventDate = new Date();
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public String getMessage() {
        return message;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public User getUser() {
        return user;
    }
}
