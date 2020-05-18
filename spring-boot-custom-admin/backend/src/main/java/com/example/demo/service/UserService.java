package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void signin(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String refresh(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new CustomException("Invalid token", HttpStatus.UNAUTHORIZED);
        }

        String username = tokenProvider.getUsernameFromToken(refreshToken);
        return tokenProvider.generateAccessToken(username).getTokenValue();
    }

    public User me(HttpServletRequest req) {
        String token = tokenProvider.resolveToken(req);
        if (token == null) {
            throw new CustomException("Invalid token", HttpStatus.UNAUTHORIZED);
        }

        User user = userRepository.findByUsername(tokenProvider.getUsernameFromToken(token))
                .orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));

        user.getRoles();
        user.getGroups();

        return user;
    }
}
