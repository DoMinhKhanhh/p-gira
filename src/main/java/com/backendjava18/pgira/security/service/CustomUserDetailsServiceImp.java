package com.backendjava18.pgira.security.service;

import com.backendjava18.pgira.common.exception.GiraBusinessException;
import com.backendjava18.pgira.role.model.User;
import com.backendjava18.pgira.role.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new GiraBusinessException("User is not existed.")
                );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
