package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;
import com.pusl2020project.groupproject.entity.Accident;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
class AccidentServiceTest {

    @Autowired
    private AccidentService accidentService;

    @Test
    void getAccidentByLoginUser() {
       List<ResponseAccidentDTO> accidentList = accidentService.getAccidentByLoginUser("root");
        for (ResponseAccidentDTO accident : accidentList) {
            log.info("==================>" + accident.toString());
        }
    }

    @Test
    @Disabled
    void deleteAccident() {
        accidentService.deleteAccident(7L);
    }
}