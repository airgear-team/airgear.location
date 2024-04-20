package com.airgear.location.dto;

import com.airgear.model.SettlementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private Integer uniqueSettlementID;
    private String settlement;
    private String region;
    private SettlementType settlementType;
}
