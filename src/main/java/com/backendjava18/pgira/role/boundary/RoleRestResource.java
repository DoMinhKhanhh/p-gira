package com.backendjava18.pgira.role.boundary;

import com.backendjava18.pgira.common.model.ResponseDTO;
import com.backendjava18.pgira.common.util.ResponseUtils;
import com.backendjava18.pgira.role.dto.RoleDTO;
import com.backendjava18.pgira.role.service.RoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/roles")
public class RoleRestResource {
    private final RoleService roleService;

    public RoleRestResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    @Transactional(readOnly = true)
    public Object findAll(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid RoleDTO roleDTO) {
        return ResponseUtils.get(roleService.save(roleDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDTO> delete(@RequestParam("code") String code) {
        roleService.delete(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{role-id}/add-operations")
    public ResponseEntity<ResponseDTO> addOperations(
            @RequestBody List<UUID> ids,
            @PathVariable("role-id") UUID roleId
    ) {
        return ResponseUtils.get(
                roleService.addOperations(roleId, ids)
                , HttpStatus.OK);
    }

    @GetMapping("paging")
    public ResponseEntity<ResponseDTO> findAllDtoPagin(@RequestParam("size") int size
            , @RequestParam("index") int index) {
        return ResponseUtils.get(
                roleService.findAllDto(Pageable.ofSize(size).withPage(index), RoleDTO.class)
                , HttpStatus.OK
        );
    }


}
