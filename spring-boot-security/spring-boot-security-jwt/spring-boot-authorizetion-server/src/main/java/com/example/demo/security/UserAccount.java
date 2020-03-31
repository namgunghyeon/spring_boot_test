package com.example.demo.security;

import com.example.demo.config.KeyProps;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends User {
    private com.example.demo.model.User user;
    //user에 포함된 정보가 아닌 추가 정보
    private KeyProps keyProps;
    public UserAccount(com.example.demo.model.User user, List<GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public void setKeyProps(KeyProps keyProps) {
        this.keyProps = keyProps;
    }

    public KeyProps getKeyProps() {
        return keyProps;
    }
}
