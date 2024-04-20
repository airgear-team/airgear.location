package com.airgear.location.service;

import com.airgear.location.dto.LocationDto;
import com.airgear.location.mapper.LocationMapper;
import com.airgear.model.Location;
import com.airgear.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public List<LocationDto> findByCityNameStartingWith(String prefix) {
        List<Location> locations = locationRepository.findBySettlementStartingWithIgnoreCase(prefix);
        return locations.stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocationDto findByUniqueSettlementId(Integer uniqueSettlementId) {
        Location location = locationRepository.findByUniqueSettlementID(uniqueSettlementId);
        return locationMapper.toDto(location);
    }
}
