package com.pusl2020project.groupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CardDetailsDTO {
    private int registeredUser;
    private float registeredUserPercentage;
    private int approvedAccident;
    private float approvedPercentage;
    private int pendingAccident;
    private float pendingPercentage;
    private int rejectedAccident;
    private float rejectedPercentage;
}
