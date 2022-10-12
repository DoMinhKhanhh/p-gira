package com.backendjava18.pgira.role.repository;

import com.backendjava18.pgira.role.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {

    @Query("select o from Operation as o left join o.roles as ro left join ro.userGroups as ug left join ug.users as ugu where ugu.username = ?2 and o.name = ?1 ")
    List<Operation> findAllByNameAndUsername(String operationName, String username);
}
