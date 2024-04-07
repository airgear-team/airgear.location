package com.airgear.location.service;

import com.airgear.location.dto.LocationDto;
import com.airgear.location.mapper.LocationMapper;
import com.airgear.location.model.Settlement;
import com.airgear.location.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final SettlementRepository settlementRepository;
    private final LocationMapper locationMapper;

    public List<LocationDto> findByCityNameStartingWith(String prefix) {
        List<Settlement> settlements = settlementRepository.findByNameStartingWithIgnoreCase(prefix);
        return settlements.stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocationDto findByUniqueSettlementId(Integer uniqueSettlementId) {
        Settlement settlement = settlementRepository.findByUniqueSettlementID(uniqueSettlementId);
        return locationMapper.toDto(settlement);
    }
}
