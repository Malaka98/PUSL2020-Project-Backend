package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.enumTypes.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class IAccidentRepositoryTest {

    @Autowired
    private IAccidentRepository iAccidentRepository;

    @Test
    void findAllByUser() {
    }

    @Test
    void countAllByVehicleType() {
    }

    @Test
    void countAccidentByStatusEquals() {
        log.info("=============>" + iAccidentRepository.countAccidentByStatusEquals(Status.Pending));
    }
}