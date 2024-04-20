package com.airgear.location.service;

import com.airgear.location.dto.SettlementData;
import com.airgear.location.dto.SettlementRequest;
import com.airgear.location.dto.SettlementResponse;
import com.airgear.model.Region;
import com.airgear.model.Location;
import com.airgear.model.SettlementType;
import com.airgear.location.repository.RegionRepository;
import com.airgear.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NovaPoshtaService {

    private final RestTemplate restTemplate;
    private final RegionRepository regionRepository;
    private final LocationRepository locationRepository;

    @Value("${novaposhta.api.key}")
    private String apiKey;

    @Transactional
    public void fetchAndSaveSettlements() {
        String modelName = "Address";
        String calledMethod = "getCities";

        String apiUrl = "https://api.novaposhta.ua/v2.0/json/";

        SettlementRequest request = new SettlementRequest();
        request.setApiKey(apiKey);
        request.setModelName(modelName);
        request.setCalledMethod(calledMethod);

        SettlementResponse response = restTemplate.postForObject(apiUrl, request, SettlementResponse.class);

        if (response != null && response.isSuccess()) {

            for (SettlementData settlementData : response.getData()) {
                String regionName = settlementData.getRegion();
                String settlementName = settlementData.getSettlement();
                Integer settlementId = settlementData.getSettlementId();
                String settlementType = settlementData.getSettlementType();

                Optional<Region> regionOptional = regionRepository.findByRegion(regionName);

                Region region;
                if (regionOptional.isPresent()) {
                    region = regionOptional.get();
                } else {
                    region = new Region();
                    region.setRegion(regionName);
                    region = regionRepository.save(region);
                }

                Optional<Location> settlementOptional = locationRepository.findBySettlement(settlementName);

                if (!settlementOptional.isPresent()) {
                    Location location = new Location();
                    location.setSettlement(settlementName);
                    location.setRegion(region);
                    location.setUniqueSettlementID(settlementId);
                    location.setSettlementType(stringToSettlementEnumType(settlementType));
                    locationRepository.save(location);
                }
            }
        }
    }

    private SettlementType stringToSettlementEnumType(String settlementType) {
        return switch (settlementType) {
            case "село" -> SettlementType.VILLAGE;
            case "селище", "селище міського типу" -> SettlementType.URBAN_VILLAGE;
            case "місто" -> SettlementType.CITY;
            default -> null;
        };
    }

}
