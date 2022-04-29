package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.entity.Accident;

public interface IAccidentService {
    void saveAccident(Accident accident, String UserName);
}
