package com.example.demo.controller;

import com.example.demo.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthorizedResource {
    @Autowired
    private AuthorityChecker authorityChecker;

    private LoginAuthorityChecker auth() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        return new LoginAuthorityChecker(customUserDetails, authorityChecker);
    }
    public void checkHasAnyRole(String ...roles) {
        auth().checkHasAnyRole(roles);
    }

    public void checkAuthority() {
    }

    @Bean
    public AuthorityChecker authorityChecker() {
        return new BasicAuthorityChecker();
    }
}
