package com.airgear.location.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementResponse {
    private boolean success;
    private List<SettlementData> data;
    private List<String> errors;
}
