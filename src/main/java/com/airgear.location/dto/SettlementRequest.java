package com.airgear.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementRequest {
    private String apiKey;
    private String modelName;
    private String calledMethod;
}
