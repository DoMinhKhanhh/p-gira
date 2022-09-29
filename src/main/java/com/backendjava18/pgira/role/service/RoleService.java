package com.backendjava18.pgira.role.service;

import com.backendjava18.pgira.role.model.Role;
import com.backendjava18.pgira.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role save(Role role);
    Role update(Role role, String code);
    void delete(String code);
}

@Service
@Transactional
class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
}
