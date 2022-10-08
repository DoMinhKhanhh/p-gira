package com.backendjava18.pgira.role.service;

import com.backendjava18.pgira.common.service.GenericService;
import com.backendjava18.pgira.common.util.GiraMapper;
import com.backendjava18.pgira.role.dto.UserDTO;
import com.backendjava18.pgira.role.model.User;
import com.backendjava18.pgira.role.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface UserService extends GenericService<User, UserDTO, UUID> {

}

@Service
class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final GiraMapper giraMapper;

    UserServiceImp(UserRepository userRepository, GiraMapper giraMapper) {
        this.userRepository = userRepository;
        this.giraMapper = giraMapper;
    }


    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return giraMapper;
    }
}
