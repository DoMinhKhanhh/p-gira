package com.backendjava18.pgira.security.dto;

import com.backendjava18.pgira.security.validation.MustBeExistedUser;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @MustBeExistedUser
    private String username;
    private String password;
}
