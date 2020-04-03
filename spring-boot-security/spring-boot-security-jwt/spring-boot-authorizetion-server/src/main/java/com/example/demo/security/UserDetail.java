package com.example.demo.security;

import com.example.demo.config.DynamicMetaConfiguration;
import com.example.demo.config.KeyProps;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetail implements UserDetailsService {
    private final UserRepository userRepository;
    private final DynamicMetaConfiguration dynamicMetaConfiguration;

    @Autowired
    public UserDetail(
            UserRepository userRepository,
            DynamicMetaConfiguration dynamicMetaConfiguration) {
        this.userRepository = userRepository;
        this.dynamicMetaConfiguration = dynamicMetaConfiguration;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        final KeyProps keyProps = dynamicMetaConfiguration.getKeyProps(username);

        if (user == null || keyProps == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        user.getRoles().size();
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        List<GrantedAuthority> authorities2 = Arrays.asList(new SimpleGrantedAuthority(keyProps.getUri()));
        return new UserAccount(user, keyProps, authorities2);

        /*
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
                */
    }

}