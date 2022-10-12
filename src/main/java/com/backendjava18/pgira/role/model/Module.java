package com.backendjava18.pgira.role.model;

import com.backendjava18.pgira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = RoleEntity.Module.TABLE_NAME)
@Entity
public class Module extends BaseEntity {

    @Column(name = RoleEntity.Module.NAME, unique = true, length = 100)
    @Length(min = 5, max = 100, message = "{role.module.name.size}")
    private String name;

    @Column(name = RoleEntity.Module.CODE, unique = true)
    @Length(min = 3, max = 10, message = "{role.module.code.size}")
    private String code;

    @NotBlank
    @Column(name = RoleEntity.Module.DESCRIPTION)
    private String description;
}
