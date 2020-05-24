package com.example.demo.controller;

import com.example.demo.dto.CustomUserDetails;
import com.example.demo.exception.CustomException;
import org.springframework.http.HttpStatus;

public class LoginAuthorityChecker {
    private final CustomUserDetails user;
    private final AuthorityChecker authorityChecker;
    public LoginAuthorityChecker(CustomUserDetails user, AuthorityChecker authorityChecker) {
        this.user = user;
        this.authorityChecker = authorityChecker;
    }

    public void checkHasAnyRole(String... roles) {
       throw new CustomException("invalid role", HttpStatus.UNAUTHORIZED);
    }
}
