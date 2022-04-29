package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.dto.UserDTO;
import com.pusl2020project.groupproject.entity.User;

import java.util.List;

public interface IUserService {
    RoleDTO saveRole(RoleDTO role);
    UserDTO saveUser(UserDTO userDTO);
    void addRoleToUser(String userName, String roleName);
    List<UserDTO> getAllUsers();
    User deleteUser(String userName);
}
