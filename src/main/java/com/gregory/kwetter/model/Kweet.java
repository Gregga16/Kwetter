package com.gregory.kwetter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Kweet implements Serializable{

    @Id
    @GeneratedValue
    private int Id;

    private String message;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventDate;

    public Kweet() {}


}
