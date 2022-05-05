package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.dto.AccidentDTO;
import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;

import java.util.List;

public interface IAccidentService {
    ResponseAccidentDTO saveAccident(AccidentDTO accidentDTO, String UserName);

    void deleteAccident(Long id);

    List<ResponseAccidentDTO> getAccidentByLoginUser(String userName);
}
