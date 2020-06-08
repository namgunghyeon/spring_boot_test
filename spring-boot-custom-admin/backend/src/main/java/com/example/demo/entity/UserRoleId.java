package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    private UserRoleId() {}

    public UserRoleId(
            Long userId,
            Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
