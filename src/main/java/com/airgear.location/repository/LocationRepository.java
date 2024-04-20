package com.airgear.location.repository;

import com.airgear.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findBySettlement(String cityName);
    List<Location> findBySettlementStartingWithIgnoreCase(String prefix);

    Location findByUniqueSettlementID(Integer uniqueSettlementId);
}
