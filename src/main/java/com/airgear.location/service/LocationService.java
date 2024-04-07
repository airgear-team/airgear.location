package com.airgear.location.service;

import com.airgear.location.dto.LocationDto;
import com.airgear.location.model.Settlement;
import com.airgear.location.repository.SettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private SettlementRepository settlementRepository;

    public List<LocationDto> findByCityNameStartingWith(String prefix) {
        List<Settlement> settlements = settlementRepository.findByNameStartingWithIgnoreCase(prefix);
        return settlements.stream()
                .map(this::convertToLocationDto)
                .collect(Collectors.toList());
    }
    public LocationDto findByUniqueSettlementId(Integer uniqueSettlementId) {
        Settlement settlement = settlementRepository.findByUniqueSettlementID(uniqueSettlementId);
        if (settlement != null) {
            return convertToLocationDto(settlement);
        } else {
            return null;
        }
    }

    private LocationDto convertToLocationDto(Settlement settlement) {
        LocationDto dto = new LocationDto();
        dto.setName(settlement.getName());
        dto.setRegion(settlement.getRegion().getName());
        dto.setSettlementType(settlement.getSettlementType());
        dto.setUniqueSettlementId(settlement.getUniqueSettlementID());
        return dto;
    }
}
