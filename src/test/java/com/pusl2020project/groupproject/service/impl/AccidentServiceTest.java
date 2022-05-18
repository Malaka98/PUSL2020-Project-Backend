package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;
import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.repository.IAccidentRepository;
import com.pusl2020project.groupproject.repository.IPhotoRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.util.List;


//@SpringBootTest
@Slf4j
class AccidentServiceTest {

    @Mock
    private IAccidentRepository iAccidentRepository;
    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private IPhotoRepository iPhotoRepository;
    private AutoCloseable autoCloseable;
    private AccidentService accidentService;

    AccidentServiceTest() {
    }

    @BeforeEach
    void setUp() throws Exception {
        autoCloseable = MockitoAnnotations.openMocks(this);
        accidentService = new AccidentService("fileStorage", iAccidentRepository, iUserRepository, iPhotoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

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