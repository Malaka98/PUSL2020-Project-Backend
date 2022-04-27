package com.pusl2020project.groupproject.service.impl;


import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.dto.UserDTO;
import com.pusl2020project.groupproject.entity.Role;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.exception.NotFoundException;
import com.pusl2020project.groupproject.repository.IRoleRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.security.CustomUserDetails;
import com.pusl2020project.groupproject.service.IUserService;
import com.pusl2020project.groupproject.util.DtoConverter.RoleDtoConverter;
import com.pusl2020project.groupproject.util.DtoConverter.UserDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final IRoleRepository roleRepository;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = iUserRepository.findUserByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }

        return new CustomUserDetails(user);
    }


    @Override
    public RoleDTO saveRole(RoleDTO role) {
        return RoleDtoConverter.roleToDto(roleRepository.save(RoleDtoConverter.dtoToRole(role)));
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = iUserRepository.save(UserDtoConverter.dtoToUser(userDTO));

        return UserDtoConverter.userToUserDTO(user);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {

        User user = iUserRepository.findUserByUsername(userName);

        if (user == null) {
            log.warn(userName + " User Not Found");
            throw new NotFoundException(userName + " User Not Found");
        }

        Role role = roleRepository.findByName(roleName);
        user.getRole().add(role);
    }
}

