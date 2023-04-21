package com.example.externalclient.controller;

import com.example.externalclient.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SimulationController {
    private final SimulationService simulationService;

    @GetMapping("/simulate")
    public ResponseEntity<Void> simulate() {
        simulationService.simulate();
        return ResponseEntity.ok().build();
    }
}
