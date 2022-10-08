package com.backendjava18.pgira.role.dto;

import com.backendjava18.pgira.role.model.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String displayName;
    private String fullname;
    private String avatar;
    private User.Status status;
}
