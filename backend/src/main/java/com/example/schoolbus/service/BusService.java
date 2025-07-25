package com.example.schoolbus.service;

import com.example.schoolbus.model.Bus;
import com.example.schoolbus.model.BusLocation;
import com.example.schoolbus.repository.BusLocationRepository;
import com.example.schoolbus.repository.BusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BusService {
    private final BusRepository busRepository;
    private final BusLocationRepository busLocationRepository;

    public BusService(BusRepository busRepository, BusLocationRepository busLocationRepository) {
        this.busRepository = busRepository;
        this.busLocationRepository = busLocationRepository;
    }

    @Transactional
    public Bus createBus(String name) {
        return busRepository.save(new Bus(name));
    }

    @Transactional
    public BusLocation updateLocation(Long busId, double latitude, double longitude) {
        Optional<Bus> bus = busRepository.findById(busId);
        if (bus.isEmpty()) {
            throw new IllegalArgumentException("Bus not found");
        }
        BusLocation location = new BusLocation(bus.get(), latitude, longitude, LocalDateTime.now());
        return busLocationRepository.save(location);
    }

    public Optional<BusLocation> getCurrentLocation(Long busId) {
        return busLocationRepository.findTop1ByBusIdOrderByTimestampDesc(busId).stream().findFirst();
    }
}
