package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;
import com.pusl2020project.groupproject.entity.Accident;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
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

    @Test
    void findAllAccident() {

        List<Accident> accidents = accidentService.getAllAccident();

        for (Accident accident : accidents) {
            log.info(accident.toString());
        }
    }
}