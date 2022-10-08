package com.backendjava18.pgira.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupWithUsersDTO implements Serializable {
    private UUID id;
    @Length(min = 5, max = 100, message = "{role.name.size}")
    private String name;

    @Length(min = 3, max = 10, message = "{role.code.size}")
    private String code;

    @NotBlank
    private String description;

    @NotBlank
    private Set<UserDTO> users = new LinkedHashSet<>();
}
