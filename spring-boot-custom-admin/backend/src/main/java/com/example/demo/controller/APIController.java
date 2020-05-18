package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Group;
import com.example.demo.entity.Service;
import com.example.demo.entity.User;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.TokenProvider;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class APIController {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(path = "/")
    public String welcome() {
        return "welcome";
    }

    @PostMapping(path = "/api/users/signup")
    public ResponseEntity signup(@RequestBody SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(signupRequest.getPassword());
        user.setUsername(signupRequest.getUsername());

        userService.signup(user);
        String token = tokenProvider.generateAccessToken(user.getUsername()).getTokenValue();
        String refreshToken =  tokenProvider.generateRefreshToken(user.getUsername()).getTokenValue();

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpCookie httpTokenCookie = ResponseCookie.from("accessToken", token).maxAge(Duration.ofSeconds(TokenProvider.tokenExpirationMsec / 1000)).httpOnly(true).path("/").build();
        HttpCookie httpRefreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken).maxAge(Duration.ofSeconds(TokenProvider.refreshTokenExpirationMsec / 1000)).httpOnly(true).path("/").build();

        responseHeaders.add(HttpHeaders.SET_COOKIE, httpTokenCookie.toString());
        responseHeaders.add(HttpHeaders.SET_COOKIE, httpRefreshTokenCookie.toString());

        Map response = new HashMap<String, String>();
        response.put("accessToken", token);
        response.put("refreshToken", refreshToken);

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @PostMapping(path = "/api/users/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());

        System.out.println(passwordEncoder.encode(loginRequest.getPassword()));
        userService.signin(loginRequest.getUsername(), loginRequest.getPassword());

        String token = tokenProvider.generateAccessToken(loginRequest.getUsername()).getTokenValue();
        String refreshToken =  tokenProvider.generateRefreshToken(loginRequest.getUsername()).getTokenValue();

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpCookie httpTokenCookie = ResponseCookie.from("accessToken", token).maxAge(Duration.ofSeconds(TokenProvider.tokenExpirationMsec / 1000)).httpOnly(true).path("/").build();
        HttpCookie httpRefreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken).maxAge(Duration.ofSeconds(TokenProvider.refreshTokenExpirationMsec / 1000)).httpOnly(true).path("/").build();

        responseHeaders.add(HttpHeaders.SET_COOKIE, httpTokenCookie.toString());
        responseHeaders.add(HttpHeaders.SET_COOKIE, httpRefreshTokenCookie.toString());

        Map response = new HashMap<String, String>();
        response.put("accessToken", token);
        response.put("refreshToken", refreshToken);
        response.put("status", "ok");

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @PostMapping(path ="/api/users/token/refresh")
    public ResponseEntity tokenRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = userService.refresh(refreshTokenRequest.getToken());
        String token = tokenProvider.generateAccessToken(tokenProvider.getUsernameFromToken(refreshToken)).getTokenValue();

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpCookie httpTokenCookie = ResponseCookie.from("accessToken", token).maxAge(Duration.ofSeconds(TokenProvider.tokenExpirationMsec / 1000)).httpOnly(true).path("/").build();
        HttpCookie httpRefreshTokenCookie = ResponseCookie.from("refreshToken", refreshToken).maxAge(Duration.ofSeconds(TokenProvider.refreshTokenExpirationMsec / 1000)).httpOnly(true).path("/").build();

        responseHeaders.add(HttpHeaders.SET_COOKIE, httpTokenCookie.toString());
        responseHeaders.add(HttpHeaders.SET_COOKIE, httpRefreshTokenCookie.toString());

        Map response = new HashMap<String, String>();
        response.put("accessToken", token);
        response.put("refreshToken", refreshToken);

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

    @GetMapping(path ="/api/currentUser")
    public ResponseEntity me(HttpServletRequest req) {
        Map response = new HashMap<String, String>();
        User user = userService.me(req);

        List<Map> groups = new ArrayList<>();
        for (Group group : user.getGroups()) {
            Map groupData = new HashMap<String, String>();
            groupData.put("id", group.getId());
            groupData.put("name", group.getName());
            groupData.put("description", group.getDescription());

            groups.add(groupData);
        }

        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("roles", user.getRoles());
        response.put("groups", groups);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path ="/api/groups")
    public ResponseEntity groups(Authentication auth) {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        User user = userRepository.findById(customUserDetails.getUser().getId()).orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));

        List<Long> ids = user.getGroups().stream().map(item -> item.getId()).collect(Collectors.toList());
        List<Group> groups = groupRepository.findByIdIn(ids).orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));
        List<Group> srGroups = groupRepository.findAllByServiceRequest().orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));

        System.out.println(srGroups.size());
        System.out.println(srGroups.get(0).getServices());

        return ResponseEntity.ok().body("");
    }

    @GetMapping(path ="/api/groups/{groupId}/services")
    public ResponseEntity groupService(@PathVariable Long groupId, Authentication auth) {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        User user = userRepository.findById(customUserDetails.getUser().getId()).orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));

        List<Group> groups = user.getGroups().stream().filter(item -> item.getId().equals(groupId)).collect(Collectors.toList());
        if (groups.size() == 0) {
            throw new CustomException("Resource Not found", HttpStatus.NOT_FOUND);
        }

        List<Map> services = new ArrayList<>();
        for (Service service : groups.get(0).getServices()) {
            Map serviceData = new HashMap<String, String>();
            serviceData.put("id", service.getId());
            serviceData.put("accessUri", service.getAccessUri());
            serviceData.put("isActivated", service.getIsActivated());

            services.add(serviceData);
        }

        Map response = new HashMap<String, String>();
        response.put("id", groups.get(0).getId());
        response.put("name", groups.get(0).getName());
        response.put("description", groups.get(0).getDescription());
        response.put("services", services);


        return ResponseEntity.ok().body(response);
    }

    @PostMapping(path ="/api/groups/{id}/services")
    @Transactional
    public ResponseEntity createService(@PathVariable Long id, Authentication auth) {
        //service 추가
        //group_service 추가
        Group group = groupRepository.findById(id).orElseThrow(() -> new CustomException("Resource Not found", HttpStatus.NOT_FOUND));
        Service service = new Service("test", 1);
        group.addService(service);
        //serviceRepository.save(service);

        //group.addService(service);

        groupRepository.save(group);
        return ResponseEntity.ok().body("");
    }

    @GetMapping(path ="/api/groups/{groupId}/services/{serviceId}")
    public ResponseEntity getService(@PathVariable Long groupId, @PathVariable Long serviceId, Authentication auth) {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        User user = userRepository.findById(customUserDetails.getUser().getId()).orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));

        Group group = user.getGroups().stream().filter(item -> item.getId().equals(groupId)).findFirst().orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));
        Service service = group.getServices().stream().filter(item -> item.getId().equals(serviceId)).findFirst().orElseThrow(() -> new CustomException("Invalid token", HttpStatus.UNAUTHORIZED));

        return ResponseEntity.ok().body(service);
    }

    @GetMapping(path ="/api/test")
    public String test() {
        return "good";
    }
}
