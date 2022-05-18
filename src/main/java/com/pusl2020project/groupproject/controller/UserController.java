package com.pusl2020project.groupproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.pusl2020project.groupproject.dto.ResponseUserDTO;
import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.dto.UserDTO;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.exception.UnknownException;
import com.pusl2020project.groupproject.service.impl.UserService;
import com.pusl2020project.groupproject.util.DtoConverter.RoleDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(HttpServletRequest request) {

        JsonObject response = new JsonObject();
        response.addProperty("user", (String) request.getSession().getAttribute("USER_NAME"));

        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(response);
    }

    @PostMapping("/validate_dashboard")
    public ResponseEntity<?> validateDashboard(HttpServletRequest request) {

        JsonObject response = new JsonObject();
        response.addProperty("user", (String) request.getSession().getAttribute("USER_NAME"));

        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(response);
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody UserDTO user) {

        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
            UserDTO userDTO = userService.saveUser(user);
            return ResponseEntity.created(uri).body(ResponseUserDTO.builder()
                    .id(userDTO.getId())
                    .name(userDTO.getName())
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .address(userDTO.getAddress())
                    .role(userDTO.getRole())
                    .build());
        } catch (Exception ex) {

            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @PutMapping("/user/{userName}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody ResponseUserDTO responseUserDTO, @PathVariable String userName) {
        try {
            userService.updateUser(userName, responseUserDTO);

            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) {

        try {
            User user = userService.deleteUser(userName);

            return ResponseEntity.ok().body(ResponseUserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .role(RoleDtoConverter.roleListToRoleDto(user.getRole()))
                    .build());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @PostMapping("/role")
    ResponseEntity<Collection<RoleDTO>> saveRole(@Valid @RequestBody Collection<RoleDTO> roleDTOS) {

        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
            return ResponseEntity.created(uri).body(userService.saveAllRole(
                    RoleDtoConverter.roleListToRoleDto(RoleDtoConverter.dtoRoleListToRole(roleDTOS))));
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @GetMapping("/role")
    ResponseEntity<Collection<RoleDTO>> getAllRoles() {
        try {
            return ResponseEntity.ok().body(userService.getAllRoles());
        } catch (Exception ex) {
            throw new UnknownException(ex.getMessage() + " ⚠⚠⚠");
        }
    }
}
