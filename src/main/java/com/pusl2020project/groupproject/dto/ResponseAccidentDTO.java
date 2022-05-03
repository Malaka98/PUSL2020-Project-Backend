package com.pusl2020project.groupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseAccidentDTO {
    private String location;
    private String description;
    private String vehicleNumber;
    private String vehicleType;
    private boolean isApproved;
    private ResponseUserDTO user;
}
