package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.enumTypes.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
@Slf4j
class IAccidentRepositoryTest {

    @Mock
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