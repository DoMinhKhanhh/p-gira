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
@Table(name = RoleEntity.UserGroup.TABLE_NAME)
@Entity
public class UserGroup extends BaseEntity {

    @Column(name = RoleEntity.UserGroup.NAME, unique = true, length = 100)
    @Length(min = 5, max = 100, message = "{role.name.size}")
    private String name;

    @Column(name = RoleEntity.UserGroup.CODE, unique = true, length = 100)
    @Length(min = 3, max = 100, message = "{role.code.size}")
    private String code;

    @Column(name = RoleEntity.UserGroup.DESCRIPTION)
    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "userGroups", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "g_group_users",
            joinColumns = @JoinColumn(name = "user_group_id")
            , inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private Set<User> users = new LinkedHashSet<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getUserGroups().add(this);
    }
}
