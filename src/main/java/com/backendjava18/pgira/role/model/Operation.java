package com.backendjava18.pgira.role.model;

import com.backendjava18.pgira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = RoleEntity.Operation.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "uc_service_g_name", columnNames = {"g_name"})
})
@Entity
public class Operation extends BaseEntity {

    @Column(name = RoleEntity.Operation.NAME, unique = true, length = 100)
    @Length(min = 5, max = 100, message = "{role.name.size}")
    private String name;

    @Column(name = RoleEntity.Operation.CODE, unique = true, length = 100)
    @Length(min = 3, max = 10, message = "{role.code.size}")
    private String code; // status-code

    @NotBlank
    @Column(name = RoleEntity.Operation.DESCRIPTION)
    private String description;

    @Column(name = RoleEntity.Operation.TYPE, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToMany(mappedBy = RoleEntity.RoleMappedOperation.OPERATION_MAPPED_ROLE)
    private Set<Role> roles = new LinkedHashSet<>();

    public enum Type {
        SAVE_OR_UPDATE,
        FETCH,
        REMOVE
    }
}
