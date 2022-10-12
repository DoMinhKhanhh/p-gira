package com.backendjava18.pgira.role.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleWithOperationsDTO {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private Set<OperationDTO> operations;
}
