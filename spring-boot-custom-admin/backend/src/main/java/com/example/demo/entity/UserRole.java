package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    public UserRole() {}

    public UserRole(User user) {
        this.user = user;
    }

}
