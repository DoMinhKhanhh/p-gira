package com.backendjava18.pgira.role.boundary;

import com.backendjava18.pgira.common.util.ResponseUtils;
import com.backendjava18.pgira.role.dto.UserDTO;
import com.backendjava18.pgira.role.model.User;
import com.backendjava18.pgira.role.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestResource {

    private final UserService userService;

    public UserRestResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAllUser() {
        return ResponseUtils.get(userService.findAllDto(UserDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseUtils.get(
                userService.save(userDTO, User.class, UserDTO.class)
                , HttpStatus.OK
        );
    }

}
