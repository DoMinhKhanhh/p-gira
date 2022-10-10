package com.backendjava18.pgira.role.service;

import com.backendjava18.pgira.common.service.GenericService;
import com.backendjava18.pgira.common.util.GiraMapper;
import com.backendjava18.pgira.role.dto.UserDTO;
import com.backendjava18.pgira.role.model.User;
import com.backendjava18.pgira.role.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


public interface UserService extends GenericService<User, UserDTO, UUID> {

    UserDTO createUser(UserDTO dto);
}

@Service
@Transactional
class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final GiraMapper giraMapper;
    private final PasswordEncoder passwordEncoder;

    UserServiceImp(UserRepository userRepository, GiraMapper giraMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.giraMapper = giraMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return giraMapper;
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        User user = giraMapper.map(dto, User.class);

        // encode passwrod
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return giraMapper.map(
                userRepository.save(user), UserDTO.class
        );
    }
}
