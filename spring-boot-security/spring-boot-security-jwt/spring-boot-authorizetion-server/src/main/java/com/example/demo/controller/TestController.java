package com.example.demo.controller;

import com.example.demo.config.KeyProps;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.dto.UserDataDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.handler.MetaUpdateEventPublisher;
import com.example.demo.model.User;
import com.example.demo.security.CurrentAccount;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.UserAccount;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class TestController {
    private ModelMapper modelMapper;
    private UserService userService;
    private MetaUpdateEventPublisher metaUpdateEventPublisher;

    @Autowired
    public TestController(
            ModelMapper modelMapper,
            UserService userService,
            MetaUpdateEventPublisher metaUpdateEventPublisher
    ) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.metaUpdateEventPublisher = metaUpdateEventPublisher;
    }

    @PostMapping("/signup")
    public TokenResponseDTO signup(@RequestBody UserDataDTO user) {
        String token = userService.signup(modelMapper.map(user, User.class), user.getRoleNames());

        return new TokenResponseDTO().convert(token);
    }

    @PostMapping("/signin")
    public TokenResponseDTO login(@RequestBody UserLoginDTO login) {
        String token = userService.signin(login.getUsername(), login.getPassword());

        return new TokenResponseDTO().convert(token);
    }

    @GetMapping(value = "/me")
    public UserResponseDTO whoami(@CurrentAccount KeyProps keyProps, HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public TokenResponseDTO refresh(HttpServletRequest req, Authentication auth) {
        String token =  userService.refresh(req.getRemoteUser() /* or auth.getName() */);

        return new TokenResponseDTO().convert(token);
    }

    @GetMapping(value = "/me2")
    @PreAuthorize("@permissionEvaluator.hasAccessRole(principal, \"/users/me2\")")
    public UserResponseDTO test(HttpServletRequest req, Authentication auth) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping(value = "/test")
    public void test() {
        metaUpdateEventPublisher.publishEvent("update");
    }
}
