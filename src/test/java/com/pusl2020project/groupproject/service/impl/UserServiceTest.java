package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.dto.UserDTO;
import com.pusl2020project.groupproject.entity.Role;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.repository.IRoleRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.util.DtoConverter.RoleDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest
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
    void saveRole() {
        userService.saveRole(RoleDTO.builder()
                .name("USER")
                .build());
        userService.saveRole(RoleDTO.builder()
                .name("POLICE_USER")
                .build());
        userService.saveRole(RoleDTO.builder()
                .name("INSURANCE_USER")
                .build());
    }

    @Test
    void saveUser() {
        Role role = roleRepository.findByName("USER");
        Role role2 = roleRepository.findByName("POLICE_USER");
        log.info("Role=============>" + role.getName());
        log.info("Role2=============>" + role2.getName());
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role2);
        userService.saveUser(UserDTO.builder()
                        .name("Malaka")
                        .username("root")
                        .password(passwordEncoder.encode("123"))
                        .email("root@gmail.com")
                        .address("abc")
                        .role(RoleDtoConverter.roleListToRoleDto(roles))
                .build());
    }

    @Test
    public void deleteUser() {
        User user = iUserRepository.findUserByUsername("root");
        log.info("*************************+++++" + user.getUsername());
        iUserRepository.delete(user);
    }
}