package com.backendjava18.pgira.role.boundary;

import com.backendjava18.pgira.common.util.ResponseUtils;
import com.backendjava18.pgira.role.dto.OperationDTO;
import com.backendjava18.pgira.role.model.Operation;
import com.backendjava18.pgira.role.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/operations")
public class OperationRestResource {

    private final OperationService operationService;

    public OperationRestResource(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseUtils.get(operationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid OperationDTO operationDTO) {
        return ResponseUtils.get(
                operationService.save(operationDTO, Operation.class, OperationDTO.class)
                , HttpStatus.OK
        );
    }
}
