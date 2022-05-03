package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.dto.ResponseAccidentDTO;
import com.pusl2020project.groupproject.entity.Accident;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAccidentService {
    void saveAccident(Accident accident, String UserName);
    String storeFile(MultipartFile file, Long accidentId);
    void deleteAccident(Long id);
    List<ResponseAccidentDTO> getAccidentByLoginUser(String userName);
}
