package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.VehicleTypeCountDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Test
    void getCountByVehicleType() {
        VehicleTypeCountDTO vehicleTypeCountDTO = reportService.getCountByVehicleType();

        log.info("Total number of bike accident ===> " + vehicleTypeCountDTO.getBike());
        log.info("Total number of car accident ===> " + vehicleTypeCountDTO.getCar());
        log.info("Total number of van accident ===> " + vehicleTypeCountDTO.getVan());
        log.info("Total number of bus accident ===> " + vehicleTypeCountDTO.getBus());
        log.info("Total number of lorry accident ===> " + vehicleTypeCountDTO.getLorry());

    }
}