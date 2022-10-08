package com.backendjava18.pgira.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

/**
 * @Data include getter, setter, default constructor, equals, toString, hashcode
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupDTO implements Serializable {

    private UUID id;

    @Length(min = 5, max = 100, message = "{role.name.size}")
    private String name;

    @Length(min = 3, max = 10, message = "{role.code.size}")
    private String code;

    @NotBlank
    private String description;
}
