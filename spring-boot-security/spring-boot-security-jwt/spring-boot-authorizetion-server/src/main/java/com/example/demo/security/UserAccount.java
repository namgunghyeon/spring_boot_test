package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends User {
    private com.example.demo.model.User user;
    private List<String> accessibleUris = new ArrayList<>();
    public UserAccount(com.example.demo.model.User user, List<GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public void addAccessibleUris(String uri) {
        this.accessibleUris.add(uri);
    }

    public List<String> getAccessibleeUris() {
        return accessibleUris;
    }
}
