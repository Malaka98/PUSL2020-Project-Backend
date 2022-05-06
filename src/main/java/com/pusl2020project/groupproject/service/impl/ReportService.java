package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.VehicleTypeCountDTO;
import com.pusl2020project.groupproject.repository.IAccidentRepository;
import com.pusl2020project.groupproject.service.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final IAccidentRepository iAccidentRepository;


    @Override
    public VehicleTypeCountDTO getCountByVehicleType() {

        return VehicleTypeCountDTO.builder()
                .bike(VehicleTypeCountDTO.Bike.builder()
                        .name("Bike")
                        .tot(Integer.toString(iAccidentRepository.countAllByVehicleType("bike")))
                        .build())
                .car(VehicleTypeCountDTO.Car.builder()
                        .name("Car")
                        .tot(Integer.toString(iAccidentRepository.countAllByVehicleType("car")))
                        .build())
                .van(VehicleTypeCountDTO.Van.builder()
                        .name("Van")
                        .tot(Integer.toString(iAccidentRepository.countAllByVehicleType("Van")))
                        .build())
                .bus(VehicleTypeCountDTO.Bus.builder()
                        .name("Bus")
                        .tot(Integer.toString(iAccidentRepository.countAllByVehicleType("bus")))
                        .build())
                .lorry(VehicleTypeCountDTO.Lorry.builder()
                        .name("Lorry")
                        .tot(Integer.toString(iAccidentRepository.countAllByVehicleType("Lorry")))
                        .build())
                .build();

    }
}
