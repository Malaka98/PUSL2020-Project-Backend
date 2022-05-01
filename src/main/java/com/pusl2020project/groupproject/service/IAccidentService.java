package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.entity.Accident;
import org.springframework.web.multipart.MultipartFile;

public interface IAccidentService {
    void saveAccident(Accident accident, String UserName);
    String storeFile(MultipartFile file, Long accidentId);
    void deleteAccident(Long id);
}
