package com.backendjava18.pgira.role.dto;

import com.backendjava18.pgira.role.validation.anontation.UniqueRoleName;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    //Data Transfer Object
    private UUID id;
    @Size(min = 5, max = 100, message = "{role.name.size}")
    @NotBlank
    @UniqueRoleName
    private String name;

    @Size(min = 5, max = 100, message = "{role.code.size}")
    @NotBlank
    private String code;

    @NotBlank(message = "{role.description.blank}")
    private String description;

}
