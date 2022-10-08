package com.backendjava18.pgira.role.service;

import com.backendjava18.pgira.common.service.GenericService;
import com.backendjava18.pgira.common.util.GiraMapper;
import com.backendjava18.pgira.role.dto.UserGroupDTO;
import com.backendjava18.pgira.role.dto.UserGroupWithUsersDTO;
import com.backendjava18.pgira.role.model.User;
import com.backendjava18.pgira.role.model.UserGroup;
import com.backendjava18.pgira.role.repository.UserGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface UserGroupService extends GenericService<UserGroup, UserGroupDTO, UUID> {

    List<UserGroupWithUsersDTO> findAllDtoIncludeUser();

    UserGroupWithUsersDTO addUsers(UUID userGroupId, List<UUID> ids);
}

@Service
@Transactional
class UserGroupServiceImp implements UserGroupService {

    private final UserGroupRepository userGroupRepository;
    private final GiraMapper giraMapper;
    private final UserService userService;

    UserGroupServiceImp(UserGroupRepository userGroupRepository, GiraMapper giraMapper, UserService userService) {
        this.userGroupRepository = userGroupRepository;
        this.giraMapper = giraMapper;
        this.userService = userService;
    }

    @Override
    public JpaRepository<UserGroup, UUID> getRepository() {
        return userGroupRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return giraMapper;
    }

    @Override
    public List<UserGroupWithUsersDTO> findAllDtoIncludeUser() {
        return userGroupRepository.findAllWithUsers()
                .stream()
                .map(model -> giraMapper.map(model, UserGroupWithUsersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserGroupWithUsersDTO addUsers(UUID userGroupId, List<UUID> ids) {
        UserGroup userGroup = userGroupRepository.findById(userGroupId)
                .orElseThrow(() -> new ValidationException("Usergroup is not existed"));
        List<User> users = userService.findByIds(ids);
        users.forEach(userGroup::addUser);
        return giraMapper.map(userGroup, UserGroupWithUsersDTO.class);
    }
}
