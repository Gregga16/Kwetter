package com.gregory.kwetter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Role implements Serializable {

    @Id
    public String roleID;

    public Role(){}

    public Role(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleID() {
        return roleID;
    }
}
