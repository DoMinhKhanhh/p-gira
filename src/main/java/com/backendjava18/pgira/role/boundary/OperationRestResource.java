package com.backendjava18.pgira.role.boundary;

import com.backendjava18.pgira.common.util.ResponseUtils;
import com.backendjava18.pgira.role.dto.OperationDTO;
import com.backendjava18.pgira.role.model.Operation;
import com.backendjava18.pgira.role.service.OperationService;
import com.backendjava18.pgira.security.authorization.GiraOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/operations")
public class OperationRestResource {

    private final OperationService operationService;

    public OperationRestResource(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    @GiraOperation(name = "findAllOperation", type = Operation.Type.FETCH)
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
