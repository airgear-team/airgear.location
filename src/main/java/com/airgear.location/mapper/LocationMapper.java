package com.airgear.location.mapper;

import com.airgear.location.dto.LocationDto;
import com.airgear.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(source = "region.region", target = "region")
    LocationDto toDto(Location location);
}
