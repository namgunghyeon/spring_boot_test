package com.example.demo.dto;

import com.example.demo.model.RoleName;

import java.util.List;

public class UserDataDTO {

    private String username;
    private String name;
    private String email;
    private String password;

    List<RoleName> roleNames;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleName> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<RoleName> roleNames) {
        this.roleNames = roleNames;
    }
}