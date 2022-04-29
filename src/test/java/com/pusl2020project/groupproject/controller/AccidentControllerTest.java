package com.pusl2020project.groupproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusl2020project.groupproject.dto.LoginDTO;
import com.pusl2020project.groupproject.entity.Accident;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveAccidentTest() throws Exception {

        String uri = "/api/accident";

        String inputJson = new ObjectMapper().writeValueAsString(Accident.builder()
                .location("ASD")
                .vehicleNumber("asd-8763")
                .description("Lorem Ipsom")
                .vehicleType("BVC")
                .build());
        String loginInputJson = new ObjectMapper().writeValueAsString(LoginDTO.builder()
                        .username("root")
                        .password("123")
                        .build());

        MvcResult loginMvcResult = mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(loginInputJson)).andReturn();

        int loginStatus = loginMvcResult.getResponse().getStatus();
        Cookie[] cookie = loginMvcResult.getResponse().getCookies();
        assertEquals(200, loginStatus);

        MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).cookie(cookie)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }
}