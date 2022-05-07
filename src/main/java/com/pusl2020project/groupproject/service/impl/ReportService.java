package com.pusl2020project.groupproject.service.impl;

import com.pusl2020project.groupproject.dto.AccidentTypePercentageDTO;
import com.pusl2020project.groupproject.dto.CardDetailsDTO;
import com.pusl2020project.groupproject.dto.VehicleTypeCountDTO;
import com.pusl2020project.groupproject.entity.enumTypes.Status;
import com.pusl2020project.groupproject.repository.IAccidentRepository;
import com.pusl2020project.groupproject.repository.IUserRepository;
import com.pusl2020project.groupproject.service.IReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final IAccidentRepository iAccidentRepository;
    private final IUserRepository iUserRepository;


    @Override
    public VehicleTypeCountDTO getCountByVehicleType() {

        return VehicleTypeCountDTO.builder()
                .bike(VehicleTypeCountDTO.Bike.builder()
                        .name("Bike")
                        .total_number_of_accident(Integer.toString(iAccidentRepository.countAllByVehicleType("bike")))
                        .build())
                .car(VehicleTypeCountDTO.Car.builder()
                        .name("Car")
                        .total_number_of_accident(Integer.toString(iAccidentRepository.countAllByVehicleType("car")))
                        .build())
                .van(VehicleTypeCountDTO.Van.builder()
                        .name("Van")
                        .total_number_of_accident(Integer.toString(iAccidentRepository.countAllByVehicleType("Van")))
                        .build())
                .bus(VehicleTypeCountDTO.Bus.builder()
                        .name("Bus")
                        .total_number_of_accident(Integer.toString(iAccidentRepository.countAllByVehicleType("bus")))
                        .build())
                .lorry(VehicleTypeCountDTO.Lorry.builder()
                        .name("Lorry")
                        .total_number_of_accident(Integer.toString(iAccidentRepository.countAllByVehicleType("Lorry")))
                        .build())
                .build();

    }

    @Override
    public AccidentTypePercentageDTO getAccidentTypePercentage() {
        float totalAccident = iAccidentRepository.countAccidentByStatusEquals(Status.Pending);
        float bikePercentage = (iAccidentRepository.countAllByVehicleType("bike") / totalAccident) * 100;
        float carPercentage = (iAccidentRepository.countAllByVehicleType("car") / totalAccident) * 100;
        float vanPercentage = (iAccidentRepository.countAllByVehicleType("Van") / totalAccident) * 100;
        float busPercentage = (iAccidentRepository.countAllByVehicleType("bus") / totalAccident) * 100;
        float lorryPercentage = (iAccidentRepository.countAllByVehicleType("Lorry") / totalAccident) * 100;

        return AccidentTypePercentageDTO.builder()
                .bike(AccidentTypePercentageDTO.Bike.builder()
                        .name("Bike")
                        .value(Math.round(bikePercentage))
                        .build())
                .car(AccidentTypePercentageDTO.Car.builder()
                        .name("Car")
                        .value(Math.round(carPercentage))
                        .build())
                .van(AccidentTypePercentageDTO.Van.builder()
                        .name("Van")
                        .value(Math.round(vanPercentage))
                        .build())
                .bus(AccidentTypePercentageDTO.Bus.builder()
                        .name("Bus")
                        .value(Math.round(busPercentage))
                        .build())
                .lorry(AccidentTypePercentageDTO.Lorry.builder()
                        .name("Lorry")
                        .value(Math.round(lorryPercentage))
                        .build())
                .build();
    }

    @Override
    public CardDetailsDTO getCardDetails() {

        float registeredUser = iUserRepository.getAllUsers();
        float pendingAccident = iAccidentRepository.countAccidentByStatusEquals(Status.Pending);
        float approvedAccident = iAccidentRepository.countAccidentByStatusEquals(Status.Approved);
        float rejectAccident = iAccidentRepository.countAccidentByStatusEquals(Status.Reject);

        return CardDetailsDTO.builder()
                .registeredUser((int)registeredUser)
                .registeredUserPercentage(registeredUser/100)
                .approvedAccident((int)approvedAccident)
                .approvedPercentage(approvedAccident/100)
                .rejectedAccident((int)rejectAccident)
                .rejectedPercentage(rejectAccident/100)
                .pendingAccident((int)pendingAccident)
                .pendingPercentage(pendingAccident/100)
                .build();
    }
}
