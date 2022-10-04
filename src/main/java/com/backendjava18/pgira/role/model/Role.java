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
import javax.print.attribute.standard.JobKOctets;
import javax.validation.constraints.NotBlank;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = RoleEntity.Role.TABLE_NAME)
public class Role extends BaseEntity {
    @Column(name = RoleEntity.Role.NAME, unique = true)
    @Length(min =  5, max = 100, message = "Role name must have length between {min} and {max}")
    private String name;

    @Column(name = RoleEntity.Role.CODE, unique = true)
    @Length(min =  5, max = 100, message = "Role code must have length between {min} and {max}")
    private String code;

    @Column(name = RoleEntity.Role.DESCRIPTION)
    @NotBlank
    private String description;

    @Override
    public boolean equals(Object obj) {
    Role roleObj = (Role) obj;
    return super.equals(obj)
            && roleObj.name.equals(this.name)
            && roleObj.code.equals(this.code);

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, code);
    }
}
