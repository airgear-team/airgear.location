package com.airgear.location.service;

import com.airgear.location.dto.SettlementData;
import com.airgear.location.dto.SettlementRequest;
import com.airgear.location.dto.SettlementResponse;
import com.airgear.location.model.Country;
import com.airgear.location.model.Region;
import com.airgear.location.model.Settlement;
import com.airgear.location.model.SettlementType;
import com.airgear.location.repository.CountryRepository;
import com.airgear.location.repository.RegionRepository;
import com.airgear.location.repository.SettlementRepository;
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
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final SettlementRepository settlementRepository;

    @Value("${novaposhta.api.key}")
    private String apiKey;

    @Transactional
    public void fetchAndSaveSettlements() {
        String modelName = "Address";
        String calledMethod = "getCities";
        Long countryId = 1L; // UA

        String apiUrl = "https://api.novaposhta.ua/v2.0/json/";

        SettlementRequest request = new SettlementRequest();
        request.setApiKey(apiKey);
        request.setModelName(modelName);
        request.setCalledMethod(calledMethod);

        SettlementResponse response = restTemplate.postForObject(apiUrl, request, SettlementResponse.class);

        if (response != null && response.isSuccess()) {
            Optional<Country> countryOptional = countryRepository.findById(countryId);

            if (countryOptional.isPresent()) {
                Country country = countryOptional.get();

                for (SettlementData settlementData : response.getData()) {
                    String regionName = settlementData.getRegion();
                    String settlementName = settlementData.getSettlement();
                    Integer settlementId = settlementData.getSettlementId();
                    String settlementType = settlementData.getSettlementType();

                    Optional<Region> regionOptional = regionRepository.findByNameAndCountry(regionName, Optional.of(country));

                    Region region;
                    if (regionOptional.isPresent()) {
                        region = regionOptional.get();
                    } else {
                        region = new Region();
                        region.setName(regionName);
                        region.setCountry(country);
                        region = regionRepository.save(region);
                    }

                    Optional<Settlement> settlementOptional = settlementRepository.findByName(settlementName);

                    if (!settlementOptional.isPresent()) {
                        Settlement settlement = new Settlement();
                        settlement.setName(settlementName);
                        settlement.setRegion(region);
                        settlement.setUniqueSettlementID(settlementId);
                        settlement.setSettlementType(stringToSettlementEnumType(settlementType));
                        settlementRepository.save(settlement);
                    }
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
