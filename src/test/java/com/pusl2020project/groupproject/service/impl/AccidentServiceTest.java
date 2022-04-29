package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.entity.Accident;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AccidentServiceTest {

    @Autowired
    private AccidentService accidentService;

    @Test
    void saveAccident() {
        accidentService.saveAccident(Accident.builder()
                        .location("abc")
                        .vehicleNumber("asd-876")
                        .description("Lorem Ipsom")
                        .vehicleType("xyz")
                .build(), "root");
    }
}