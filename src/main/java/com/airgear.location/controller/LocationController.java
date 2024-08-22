package com.airgear.location.controller;


import com.airgear.location.dto.LocationDto;
import com.airgear.location.service.LocationService;
import com.airgear.location.service.NovaPoshtaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private NovaPoshtaService novaPoshtaService;


    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/locations")
    public ResponseEntity<Page<LocationDto>> getLocationsByCityPrefix(@RequestParam String prefix, Pageable pageable) {
        if (prefix == null || prefix.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(locationService.findByCityNameStartingWith(prefix, pageable));
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
