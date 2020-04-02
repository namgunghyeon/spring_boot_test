package com.example.demo.security;

import com.example.demo.config.DynamicMetaConfiguration;
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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DynamicMetaConfiguration dynamicMetaConfiguration;

    //12121-1212121-111-21212-121212121-121212121 -> sha-256('test', '12121-1212121-111-21212-121212121-121212121') -> b49d3b6e794c644528970e844396cac36f1667586f79412bc7514a34eef2854a -> base64 ->
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        user.getRoles().size();
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        List<GrantedAuthority> authorities2 = Arrays.asList(new SimpleGrantedAuthority(dynamicMetaConfiguration.getKeyProps(username).getUri()));
        UserAccount userAccount = new UserAccount(user, authorities);
        userAccount.setKeyProps(dynamicMetaConfiguration.getKeyProps(username));

        return userAccount;
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