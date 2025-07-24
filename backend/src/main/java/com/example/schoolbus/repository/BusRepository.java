package com.example.schoolbus.repository;

import com.example.schoolbus.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
