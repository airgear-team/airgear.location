package com.airgear.location.repository;

import com.airgear.location.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, Long> {

    Optional<Settlement> findByName(String cityName);
    List<Settlement> findByNameStartingWithIgnoreCase(String prefix);
}
