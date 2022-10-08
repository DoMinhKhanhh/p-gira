package com.backendjava18.pgira.role.repository;

import com.backendjava18.pgira.role.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
}
