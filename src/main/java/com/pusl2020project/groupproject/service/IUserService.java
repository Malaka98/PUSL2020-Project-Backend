package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.dto.UserDTO;

public interface IUserService {
    RoleDTO saveRole(RoleDTO role);
    UserDTO saveUser(UserDTO userDTO);
    void addRoleToUser(String userName, String roleName);
}
