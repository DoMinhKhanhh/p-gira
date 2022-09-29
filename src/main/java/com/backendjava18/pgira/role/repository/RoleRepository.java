package com.backendjava18.pgira.role.repository;

import com.backendjava18.pgira.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByCode(String code);
    void deleteByCode(String code);

}

