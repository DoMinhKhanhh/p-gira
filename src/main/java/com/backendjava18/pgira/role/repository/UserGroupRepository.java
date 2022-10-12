package com.backendjava18.pgira.role.repository;

import com.backendjava18.pgira.role.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {

    @Query("select ug from UserGroup ug left join fetch ug.users")
    List<UserGroup> findAllWithUsers();
}
