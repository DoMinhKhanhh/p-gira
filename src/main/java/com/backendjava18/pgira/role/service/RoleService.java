package com.backendjava18.pgira.role.service;

import com.backendjava18.pgira.common.service.GenericService;
import com.backendjava18.pgira.common.util.GiraMapper;
import com.backendjava18.pgira.role.dto.RoleDTO;
import com.backendjava18.pgira.role.model.Role;
import com.backendjava18.pgira.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService extends GenericService<Role,RoleDTO, UUID> {
    List<Role> findAll();
    Role save(Role role);
    Role update(Role role, String code);
    void delete(String code);
    RoleDTO save(RoleDTO roleDTO);
}

@Service
@Transactional
class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private final GiraMapper giraMapper;

    public RoleServiceImpl(RoleRepository roleRepository, GiraMapper giraMapper) {
        this.roleRepository = roleRepository;
        this.giraMapper = giraMapper;
    }

    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return this.roleRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.giraMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role, String code) {
        Role currRole = roleRepository.findByCode(code);
        currRole.setName(role.getName());
        currRole.setDescription(role.getDescription());
        return roleRepository.save(currRole);
    }

    @Override
    public void delete(String code) {
        roleRepository.deleteByCode(code);
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role model = giraMapper.map(roleDTO, Role.class);
        Role savedModel = roleRepository.save(model);
        return giraMapper.map(savedModel, RoleDTO.class);
    }


}
