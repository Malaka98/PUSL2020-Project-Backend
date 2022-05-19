package com.pusl2020project.groupproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusl2020project.groupproject.dto.LoginDTO;
import com.pusl2020project.groupproject.dto.RoleDTO;
import com.pusl2020project.groupproject.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsers() throws Exception {
        String uri = "/api/users";

        String loginInputJson = new ObjectMapper().writeValueAsString(LoginDTO.builder()
                .username("root")
                .password("123")
                .build());

        MvcResult loginMvcResult = mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(loginInputJson)).andReturn();

        int loginStatus = loginMvcResult.getResponse().getStatus();
        Cookie[] cookie = loginMvcResult.getResponse().getCookies();
        assertEquals(200, loginStatus);

        MvcResult mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).cookie(cookie)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        log.info("Roles ============>" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    void saveUser() throws Exception {
        String uri = "/api/user";

        String inputData = new ObjectMapper().writeValueAsString(UserDTO.builder()
                .name("Sanduni")
                .username("sanduni")
                .email("rootx@gmail.com")
                .password("123")
                .address("Localhost")
                .role(List.of(RoleDTO.builder()
                        .id(1L)
                        .name("USER")
                        .build(), RoleDTO.builder()
                        .id(1L)
                        .name("POLICE_USER")
                        .build()))
                .build());

        MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputData)
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        log.info("Roles ============>" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void saveRole() throws Exception {
        String uri = "/api/role";
        List<String> inputDataList = new ArrayList<>();
        String loginInputJson = new ObjectMapper().writeValueAsString(LoginDTO.builder()
                .username("root")
                .password("123")
                .build());

        String inputData = new ObjectMapper().writeValueAsString(RoleDTO.builder()
                .id(3L)
                .name("INSURANCE_USER")
                .build());

        inputDataList.add(inputData);

        MvcResult loginMvcResult = mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(loginInputJson)).andReturn();

        int loginStatus = loginMvcResult.getResponse().getStatus();
        Cookie[] cookie = loginMvcResult.getResponse().getCookies();
        assertEquals(200, loginStatus);

        MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).cookie(cookie)
                .content(String.valueOf(inputDataList))
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        log.info("Roles ============>" + mvcResult.getResponse().getContentAsString());
    }

    @Test
    void getAllRoles() throws Exception {
        String uri = "/api/role";

        MvcResult mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        log.info("Roles ============>" + mvcResult.getResponse().getContentAsString());
    }
}