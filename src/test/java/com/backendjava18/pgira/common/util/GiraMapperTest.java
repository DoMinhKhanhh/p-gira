package com.backendjava18.pgira.common.util;

import com.backendjava18.pgira.role.dto.UserDTO;
import com.backendjava18.pgira.role.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GiraMapperTest {
    GiraMapper giraMapper = new GiraMapper();

    @Test
    void testUserMapper() {
        UserDTO userDTO = UserDTO.builder()
                .username("KhanhDZ")
                .email("test123@gmail.com")
                .build();

        User user = giraMapper.map(userDTO, User.class);
        Assertions.assertEquals(userDTO.getUsername(), user.getUsername());
    }
}
