package com.airgear.location.dto;

import com.airgear.location.model.SettlementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private Integer uniqueSettlementId;
    private String name;
    private String region;
    private SettlementType settlementType;
}
