package com.pusl2020project.groupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VehicleTypeCountDTO {
    private Bike bike;
    private Car car;
    private Van van;
    private Bus bus;
    private Lorry lorry;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Bike {
        private String name;
        private String total_number_of_accident;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Car {
        private String name;
        private String total_number_of_accident;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Van {
        private String name;
        private String total_number_of_accident;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Bus {
        private String name;
        private String total_number_of_accident;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class Lorry {
        private String name;
        private String total_number_of_accident;
    }
}

