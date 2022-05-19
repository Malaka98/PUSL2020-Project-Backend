package com.pusl2020project.groupproject.dto;

import com.pusl2020project.groupproject.entity.enumTypes.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChangeStatusDTO {
    private Long id;
    private Status status;
}
