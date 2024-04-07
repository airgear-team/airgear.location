package com.airgear.location.controller;


import com.airgear.location.dto.LocationDto;
import com.airgear.location.service.LocationService;
import com.airgear.location.service.NovaPoshtaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private NovaPoshtaService novaPoshtaService;


    @GetMapping("/locations")
    public ResponseEntity<List<LocationDto>> getLocationsByCityPrefix(@RequestParam String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<LocationDto> locations = locationService.findByCityNameStartingWith(prefix);
        if (locations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(locations);
    }
    @GetMapping("/{uniqueSettlementId}")
    public ResponseEntity<LocationDto> getLocationByUniqueSettlementId(@PathVariable Integer uniqueSettlementId) {
        LocationDto locationDto = locationService.findByUniqueSettlementId(uniqueSettlementId);
        if (locationDto != null) {
            return ResponseEntity.ok(locationDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/update-base")
    public ResponseEntity<String> fetchAndSaveSettlements() {
        novaPoshtaService.fetchAndSaveSettlements();
        return ResponseEntity.ok("Database updated successfully");
    }

}
