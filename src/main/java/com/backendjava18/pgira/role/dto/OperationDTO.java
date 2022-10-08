package com.backendjava18.pgira.role.dto;

import com.backendjava18.pgira.role.model.Operation;
import com.backendjava18.pgira.role.validation.anontation.UniqueRoleName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    private UUID id;

    @NotBlank
    @UniqueRoleName
    @Size(min = 5, max = 100, message = "{role.name.size}")
    private String name;

    @NotBlank
    @Size(min = 3, max = 10, message = "{role.code.size}")
    private String code;

    @NotBlank(message = "{role.description.blank}")
    private String description;

    private Operation.Type type;
}
