package com.backendjava18.pgira.role.service;

import com.backendjava18.pgira.common.service.GenericService;
import com.backendjava18.pgira.role.dto.OperationDTO;
import com.backendjava18.pgira.role.model.Operation;
import com.backendjava18.pgira.role.repository.OperationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface OperationService extends GenericService<Operation, OperationDTO, UUID> {

    List<Operation> findAll(List<UUID> operationIds);
}


@Service
@Transactional
class OperationServiceImp implements OperationService {

    private final OperationRepository operationRepository;
    private final ModelMapper modelMapper;

    OperationServiceImp(OperationRepository operationRepository, ModelMapper modelMapper) {
        this.operationRepository = operationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JpaRepository<Operation, UUID> getRepository() {
        return operationRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public List<Operation> findAll(List<UUID> operationIds) {
        return operationRepository.findAllById(operationIds);
    }
}
