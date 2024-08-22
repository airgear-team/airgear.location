package com.airgear.location.repository;

import com.airgear.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findBySettlement(String cityName);
    Page<Location> findBySettlementStartingWithIgnoreCase(String prefix, Pageable page);

    Location findByUniqueSettlementID(Integer uniqueSettlementId);
}
