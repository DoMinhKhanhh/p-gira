package com.backendjava18.pgira.security.service;

import com.backendjava18.pgira.common.exception.GiraBusinessException;
import com.backendjava18.pgira.role.model.User;
import com.backendjava18.pgira.role.repository.UserRepository;
import com.backendjava18.pgira.security.dto.LoginDTO;
import com.backendjava18.pgira.security.jwt.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(LoginDTO dto);
}

@Service
class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    AuthServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public String login(LoginDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(
                        () -> new GiraBusinessException("user is not existed")
                );

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            // return jwt
            return jwtUtils.generateJwt(dto.getUsername());
        }

        throw new GiraBusinessException("Password is not correct.");
    }
}
