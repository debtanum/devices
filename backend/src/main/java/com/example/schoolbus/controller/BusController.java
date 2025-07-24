package com.example.schoolbus.controller;

import com.example.schoolbus.model.Bus;
import com.example.schoolbus.model.BusLocation;
import com.example.schoolbus.service.BusService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/buses")
public class BusController {
    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping
    public ResponseEntity<Bus> createBus(@RequestParam @NotBlank String name) {
        Bus bus = busService.createBus(name);
        return ResponseEntity.ok(bus);
    }

    @PostMapping("/{busId}/location")
    public ResponseEntity<BusLocation> updateLocation(@PathVariable Long busId,
                                           @RequestParam @NotNull Double latitude,
                                           @RequestParam @NotNull Double longitude) {
        BusLocation location = busService.updateLocation(busId, latitude, longitude);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{busId}/location")
    public ResponseEntity<BusLocation> getCurrentLocation(@PathVariable Long busId) {
        Optional<BusLocation> location = busService.getCurrentLocation(busId);
        return location.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
