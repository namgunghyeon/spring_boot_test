package com.example.demo.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private JwtTokenHandler jwtTokenHandler;

    public JwtTokenFilterConfig(JwtTokenHandler jwtTokenProvider) {
        this.jwtTokenHandler = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenHandler);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
