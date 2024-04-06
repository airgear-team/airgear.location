package com.airgear.location.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementData {
    @JsonProperty("Description")
    private String settlement;

    @JsonProperty("AreaDescription")
    private String region;

    @JsonProperty("CityID")
    private Integer settlementId;

    @JsonProperty("SettlementTypeDescription")
    private String settlementType;
}

