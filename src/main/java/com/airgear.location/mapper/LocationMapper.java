package com.airgear.location.mapper;

import com.airgear.location.dto.LocationDto;
import com.airgear.location.model.Settlement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(source = "region.name", target = "region")
    LocationDto toDto(Settlement settlement);
}
