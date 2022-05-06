package com.pusl2020project.groupproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusl2020project.groupproject.dto.AccidentDTO;
import com.pusl2020project.groupproject.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
        MultipartFile[] file = new MultipartFile[1];


        try {
            FileInputStream fileInputStream = new FileInputStream("/home/root-x/Downloads/photo_2021-08-29_23-54-17.jpg");
            file[0] = new MockMultipartFile("files", "photo_2021-08-29_23-54-17.jpg",
                    String.valueOf(MediaType.IMAGE_JPEG), fileInputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        String loginInputJson = new ObjectMapper().writeValueAsString(LoginDTO.builder()
                .username("root")
                .password("123")
                .build());

        MvcResult loginMvcResult = mockMvc.perform(post("/api/login").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(loginInputJson)).andReturn();

        int loginStatus = loginMvcResult.getResponse().getStatus();
        Cookie[] cookie = loginMvcResult.getResponse().getCookies();
        assertEquals(200, loginStatus);

        MvcResult mvcResult = mockMvc.perform(multipart(uri)
                .flashAttr("accidentModel", AccidentDTO.builder()
                        .location("ABC")
                        .vehicleType("Car")
                        .vehicleNumber("ABC-6506")
                        .description("Lorem")
                        .files(file)
                        .build())
                .cookie(cookie)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }
}