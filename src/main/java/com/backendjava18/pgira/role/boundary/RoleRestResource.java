package com.backendjava18.pgira.role.boundary;

import com.backendjava18.pgira.common.util.ResponseUtils;
import com.backendjava18.pgira.role.dto.RoleDTO;
import com.backendjava18.pgira.role.model.Role;
import com.backendjava18.pgira.role.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleRestResource {
    private final RoleService roleService;

    public RoleRestResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public Object findAll(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public Object save(@RequestBody @Valid RoleDTO roleDTO){
       // return new ResponseEntity<>(roleService.save(roleDTO), HttpStatus.CREATED);
        return ResponseUtils.get(roleService.save(roleDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("")
    public Object delete(@RequestParam("code") String code){
        roleService.delete(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
