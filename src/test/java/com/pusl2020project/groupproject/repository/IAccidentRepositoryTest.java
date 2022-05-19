package com.pusl2020project.groupproject.repository;

import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.entity.User;
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
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
@Slf4j
class IAccidentRepositoryTest {

    @Autowired
    private IAccidentRepository iAccidentRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Test
    void findAllByUser() {
        User user = iUserRepository.findUserByUsername("root");
        List<Accident> accidents = iAccidentRepository.findAllByUser(user);

        assertNotNull(user);
        assertNotNull(accidents);

        for(Accident accident : accidents) {
            log.info("===============>>>>>>>" + accident.toString());
        }
    }

    @Test
    void countAllByVehicleType() {
    }

    @Test
    void countAccidentByStatusEquals() {

        log.info("=============>" + iAccidentRepository.countAccidentByStatusEquals(Status.Pending));
    }
}