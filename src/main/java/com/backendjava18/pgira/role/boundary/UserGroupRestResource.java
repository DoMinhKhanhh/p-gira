package com.backendjava18.pgira.role.boundary;

import com.backendjava18.pgira.common.util.ResponseUtils;
import com.backendjava18.pgira.role.dto.UserGroupDTO;
import com.backendjava18.pgira.role.model.UserGroup;
import com.backendjava18.pgira.role.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usergroup")
public class UserGroupRestResource {
    private final UserGroupService service;

    public UserGroupRestResource(UserGroupService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAllUser() {
        return ResponseUtils.get(
                service.findAllDto(UserGroupDTO.class)
                , HttpStatus.OK
        );
    }

    @GetMapping("/include-users")
    public ResponseEntity<?> findAllUserGroupIncludedUsers() {
        return ResponseUtils.get(
                service.findAllDtoIncludeUser()
                , HttpStatus.OK
        );
    }

    @PostMapping("{user-group-id}/add-user")
    public ResponseEntity<?> addUser(
            @PathVariable("user-group-id") UUID userGroupId,
            @RequestBody List<UUID> ids
    ) {
        return ResponseUtils.get(
                service.addUsers(userGroupId, ids)
                , HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> saveUsergroup(@RequestBody @Valid UserGroupDTO userGroupDTO) {
        return ResponseUtils.get(
                service.save(userGroupDTO, UserGroup.class, UserGroupDTO.class)
                , HttpStatus.CREATED
        );
    }


}
