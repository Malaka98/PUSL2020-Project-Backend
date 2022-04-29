package com.pusl2020project.groupproject.controller;

import com.pusl2020project.groupproject.dto.ResponseUserDTO;
import com.pusl2020project.groupproject.dto.UserDTO;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.exception.UnknownExeception;
import com.pusl2020project.groupproject.service.impl.UserService;
import com.pusl2020project.groupproject.util.DtoConverter.RoleDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
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

            throw new UnknownExeception(ex.getMessage());
        }
    }

    @PutMapping("/user/{userName}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody ResponseUserDTO responseUserDTO, @PathVariable String userName) {
        try {
            userService.updateUser(userName, responseUserDTO);

            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception ex) {
            throw new UnknownExeception(ex.getMessage());
        }
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) {

        User user = userService.deleteUser(userName);

        return ResponseEntity.ok().body(ResponseUserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .role(RoleDtoConverter.roleListToRoleDto(user.getRole()))
                .build());
    }
}
