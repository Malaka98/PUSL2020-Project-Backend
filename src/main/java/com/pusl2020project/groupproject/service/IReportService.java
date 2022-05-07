package com.pusl2020project.groupproject.service;

import com.pusl2020project.groupproject.dto.AccidentTypePercentageDTO;
import com.pusl2020project.groupproject.dto.CardDetailsDTO;
import com.pusl2020project.groupproject.dto.VehicleTypeCountDTO;

public interface IReportService {
    VehicleTypeCountDTO getCountByVehicleType();

    AccidentTypePercentageDTO getAccidentTypePercentage();

    CardDetailsDTO getCardDetails();
}
