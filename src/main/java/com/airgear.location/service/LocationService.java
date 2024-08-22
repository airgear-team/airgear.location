package com.airgear.location.service;

import com.airgear.location.dto.LocationDto;
import com.airgear.location.mapper.LocationMapper;
import com.airgear.model.Location;
import com.airgear.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public Page<LocationDto> findByCityNameStartingWith(String prefix, Pageable pageable) {
        Page<Location> locations = locationRepository.findBySettlementStartingWithIgnoreCase(prefix, pageable);
        return locations.map(locationMapper::toDto);
    }

    public LocationDto findByUniqueSettlementId(Integer uniqueSettlementId) {
        Location location = locationRepository.findByUniqueSettlementID(uniqueSettlementId);
        return locationMapper.toDto(location);
    }
}
