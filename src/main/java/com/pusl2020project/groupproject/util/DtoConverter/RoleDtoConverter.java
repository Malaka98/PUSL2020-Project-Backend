package com.pusl2020project.groupproject.util.DtoConverter;


import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.entity.Role;
import com.pusl2020project.groupproject.exception.UnknownException;

import java.util.ArrayList;
import java.util.Collection;

public class RoleDtoConverter {

    public static Role dtoToRole(RoleDTO roleDTO) {
        if (roleDTO != null) {
            return Role.builder()
                    .id(roleDTO.getId())
                    .name(roleDTO.getName())
                    .build();
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", roleDTO.getClass().getName()));
        }
    }

    public static RoleDTO roleToDto(Role role) {
        if (role != null) {
            return RoleDTO.builder()
                    .id(role.getId())
                    .name(role.getName())
                    .build();
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", role.getClass().getName()));
        }
    }

    public static Collection<Role> dtoRoleListToRole(Collection<RoleDTO> roleList) {
        if (roleList != null) {
            Collection<Role> roles = new ArrayList<>();

            for (RoleDTO role : roleList) {
                roles.add(RoleDtoConverter.dtoToRole(role));
            }

            return roles;
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", roleList.getClass().getName()));
        }
    }

    public static Collection<RoleDTO> roleListToRoleDto(Collection<Role> roleList) {
        if (roleList != null) {
            Collection<RoleDTO> roleDTOS = new ArrayList<>();

            for (Role role : roleList) {
                roleDTOS.add(RoleDtoConverter.roleToDto(role));
            }

            return roleDTOS;
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", roleList.getClass().getName()));
        }
    }

}
