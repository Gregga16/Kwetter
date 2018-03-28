package com.gregory.kwetter.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Kweet.findAllKweets", query = "SELECT kweet FROM Kweet kweet"),
        @NamedQuery(name = "Kweet.findById", query = "SELECT kweet FROM Kweet kweet WHERE kweet.id = :id"),
        @NamedQuery(name = "Kweet.findKweetsOnText",
                query = "SELECT kweet from Kweet kweet WHERE lower(kweet.message) LIKE :searchText")
})
public class Kweet implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String message;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;
    
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "tweet_likes")
    private Set<User> likes = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "tweet_mentions")
    private Set<User> mentions = new HashSet<>();

    public Kweet() {}

    public Kweet(String message, User user) {
        this.message = message;
        this.eventDate = new Date();
        this.user = user;
    }

    public Long getId() {
        return id;
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

    public Set<User> getLikes() {
        if (likes == null) {
            likes = new HashSet<>();
        }
        return likes;
    }

    public Set<User> getMentions() {
        if (mentions == null) {
            mentions = new HashSet<>();
        }
        return mentions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addMention(User user) {
        this.mentions.add(user);
    }

    public boolean like(User user) {
        if (user == null) {
            return false;
        }
        return likes.add(user);
    }
}
