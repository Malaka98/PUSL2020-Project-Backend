package com.pusl2020project.groupproject.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class IUserRepositoryTest {

    @Autowired
    private IUserRepository iUserRepository;

    @Test
    void findUserByUsername() {
    }

    @Test
    void getAllUsers() {
        log.info("======================>>>>>>>>>" + iUserRepository.getAllUsers());
    }
}