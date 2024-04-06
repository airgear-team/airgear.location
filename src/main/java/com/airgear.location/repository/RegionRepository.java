package com.airgear.location.repository;

import com.airgear.location.model.Country;
import com.airgear.location.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByNameAndCountry(String regionName, Optional<Country> country);
}
