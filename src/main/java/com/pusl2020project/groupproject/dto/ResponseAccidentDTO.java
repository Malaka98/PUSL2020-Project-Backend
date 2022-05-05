package com.pusl2020project.groupproject.dto;

import com.pusl2020project.groupproject.entity.enumTypes.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseAccidentDTO {
    private String location;
    private String description;
    private String vehicleNumber;
    private String vehicleType;
    private Status approved;
    private List<String> url;
    private ResponseUserDTO user;
}
