package com.example.schoolbus.repository;

import com.example.schoolbus.model.BusLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusLocationRepository extends JpaRepository<BusLocation, Long> {
    List<BusLocation> findTop1ByBusIdOrderByTimestampDesc(Long busId);
}
