package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.entity.Accident;
import com.pusl2020project.groupproject.entity.User;
import com.pusl2020project.groupproject.repository.IAccidentRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.service.IAccidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccidentService implements IAccidentService {

    private final IAccidentRepository iAccidentRepository;
    private final IUserRepository iUserRepository;

    @Override
    @Transactional
    public void saveAccident(Accident accident, String userName) {

        User user = iUserRepository.findUserByUsername(userName);
        accident.setUser(user);
        iAccidentRepository.save(accident);
    }
}
