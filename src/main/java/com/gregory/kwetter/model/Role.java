package com.gregory.kwetter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({ @NamedQuery(name = "RoleGroup.getByRoleID", query = "SELECT role FROM Role role WHERE role.roleID LIKE :roleID"),
        @NamedQuery(name = "RoleGroup.getAll", query = "SELECT role FROM Role role")})
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

    @Override
    public String toString() {
        return this.roleID;
    }
}
