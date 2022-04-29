package com.pusl2020project.groupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseUserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String address;
    private Collection<RoleDTO> role;
}
