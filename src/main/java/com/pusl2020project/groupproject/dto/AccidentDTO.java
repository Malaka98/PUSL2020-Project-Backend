package com.pusl2020project.groupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccidentDTO {
    private String location;
    private String description;
    private String vehicleNumber;
    private String vehicleType;
    private MultipartFile[] files;
}
