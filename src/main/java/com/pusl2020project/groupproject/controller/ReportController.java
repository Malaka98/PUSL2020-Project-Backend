package com.pusl2020project.groupproject.controller;

import com.pusl2020project.groupproject.dto.VehicleTypeCountDTO;
import com.pusl2020project.groupproject.service.impl.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("accident_type")
    public ResponseEntity<?> getCountByVehicleType() {

        List<Object> response = new ArrayList<>();
        VehicleTypeCountDTO vehicleTypeCountDTO = reportService.getCountByVehicleType();

        response.add(vehicleTypeCountDTO.getBike());
        response.add(vehicleTypeCountDTO.getCar());
        response.add(vehicleTypeCountDTO.getVan());
        response.add(vehicleTypeCountDTO.getBus());
        response.add(vehicleTypeCountDTO.getLorry());

        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(response);
    }
}
