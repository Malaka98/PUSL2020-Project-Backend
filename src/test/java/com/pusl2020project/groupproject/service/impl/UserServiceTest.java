package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.dto.UserDTO;
import com.pusl2020project.groupproject.entity.Role;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.repository.IRoleRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.util.DtoConverter.RoleDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class UserServiceTest {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Order(1)
    void saveRole() {
        userService.saveRole(RoleDTO.builder()
                        .id(1L)
                        .name("USER")
                        .build());
        userService.saveRole(RoleDTO.builder()
                        .id(2L)
                        .name("POLICE_USER")
                        .build());
        userService.saveRole(RoleDTO.builder()
                        .id(3L)
                        .name("INSURANCE_USER")
                        .build());
    }

    @Test
    @Order(2)
    void saveUser() {
        Role role = roleRepository.findByName("USER");
        Role role2 = roleRepository.findByName("POLICE_USER");
        log.info("Role=============>" + role.getName());
        log.info("Role2=============>" + role2.getName());
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role2);
        UserDTO userDTO = userService.saveUser(UserDTO.builder()
                        .name("Sanduni")
                        .username("root")
                        .password("123")
                        .email("sanduni@gmail.com")
                        .address("abc")
                        .role(RoleDtoConverter.roleListToRoleDto(roles))
                .build());

        assertInstanceOf(UserDTO.class, userDTO);
        assertNotNull(userDTO);

    }

    @Test
    @Disabled
    public void deleteUser() {
        User user = iUserRepository.findUserByUsername("root");
        log.info("*************************+++++" + user.getUsername());
        iUserRepository.delete(user);
    }
}