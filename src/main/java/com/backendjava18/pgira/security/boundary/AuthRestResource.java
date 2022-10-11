package com.backendjava18.pgira.security.boundary;

import com.backendjava18.pgira.common.util.ResponseUtils;
import com.backendjava18.pgira.security.dto.LoginDTO;
import com.backendjava18.pgira.security.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthRestResource {
    private final AuthService authService;

    public AuthRestResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseUtils.get(authService.login(loginDTO), HttpStatus.OK);
    }
}
